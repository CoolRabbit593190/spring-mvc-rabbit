package com.rabbit.java.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rabbit.java.common.annotation.DataSource;
import com.rabbit.java.common.constant.Constants;
import com.rabbit.java.common.enums.MasterSlave;
import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.exception.lifepay.LifePayException;
import com.rabbit.java.common.result.CommonErrorMessage;
import com.rabbit.java.common.util.AssertUtils;
import com.rabbit.java.dao.LifePayBillDAO;
import com.rabbit.java.dao.LifePayFamilyDAO;
import com.rabbit.java.dao.LifePayPowerUseRecordDAO;
import com.rabbit.java.dao.LifePayRecordDAO;
import com.rabbit.java.domain.dto.LifePayRecordDTO;
import com.rabbit.java.domain.dto.params.LifePayRecordPDTO;
import com.rabbit.java.domain.model.*;
import com.rabbit.java.service.LifePayRecordService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Mr.Rabbit
 */
@Service
public class LifePayRecordServiceImpl implements LifePayRecordService {
    @Autowired
    LifePayRecordDAO lprDAO;
    @Autowired
    LifePayFamilyDAO lpfDAO;
    @Autowired
    LifePayBillDAO lpbDAO;
    @Autowired
    LifePayPowerUseRecordDAO lppurDAO;

    public void test() {
        LifePayRecordService lifePayRecordService = (LifePayRecordService) AopContext.currentProxy();
        //  取得当前代理以后可以调用带有SpringAop注解的方法 解决直接调用方法引起动态代理失效
    }

    @DataSource(MasterSlave.MASTER)
    public void removeCostRecord(LifePayRecordPDTO paramsDTO) {

    }


    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public Object generateCostRecordByRecordMonth(LifePayRecordPDTO paramsDTO) throws CommonException {

        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Integer billMonth = paramsDTO.getBillMonth();
        if (null == billMonth) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        LifePayBillDO entity = new LifePayBillDO();
        entity.setRecordMonth(billMonth);
        LifePayBillDO bill = lpbDAO.selectOne(entity);
        if (null == bill) {
            //todo 抛出当月账单还未生成异常
            throw new LifePayException(CommonErrorMessage.LIFEPAY_NO_BILL_GENERATOR_ERR);
        }
        Double powerBill = bill.getPowerBill();
        Double waterBill = bill.getWaterBill();
        List<LifePayFamilyDO> familys = lpfDAO.selectList(new EntityWrapper<LifePayFamilyDO>());
        if (AssertUtils.isListEmpty(familys)) {
            //todo 抛出找不到用户异常
            throw new LifePayException(CommonErrorMessage.LIFEPAY_NO_FAMILY_ERR);
        }
        List<LifePayPowerUseRecordDO> powerUseRecords = lppurDAO.selectList(
                new EntityWrapper<LifePayPowerUseRecordDO>()
                        .eq("record_month", billMonth)
                        .isNull("family_id")
        );

        if (CollectionUtils.isEmpty(powerUseRecords)) {
            throw new LifePayException(CommonErrorMessage.LIFEPAY_NO_COMMON_POWER_USE_RECORD_ERR);
        }
        LifePayPowerUseRecordDO powerCommonUseRecord = powerUseRecords.get(0);
        Double powerCommonUse = powerCommonUseRecord.getPowerUse();
        //TODO 如果处理电费出现异常，捕获
        Map<Integer, Double> powerCostMap = null;
        try {
            powerCostMap = dealPowerBill(powerCommonUse, billMonth, powerBill, familys);
        } catch (CommonException e) {
            e.printStackTrace();
            return Constants.ERR;
        }
        //TODO 如果处理水费出现异常，捕获
        Map<Integer, Double> waterCostMap = null;
        try {
            waterCostMap = dealWaterBill(waterBill, familys);
        } catch (CommonException e) {
            e.printStackTrace();
            return Constants.ERR;
        }
        for (LifePayFamilyDO family : familys) {
            Integer familyId = family.getFamilyId();
            LifePayRecordDO record = new LifePayRecordDO();
            record.setFamily(family);
            record.setPowerCost(powerCostMap.get(familyId));
            record.setWaterCost(waterCostMap.get(familyId));
            record.setRecordMonth(billMonth);
            if (!CollectionUtils.isEmpty(lprDAO.selectList(
                    new EntityWrapper<LifePayRecordDO>()
                            .eq("record_month", billMonth)
                            .eq("family_id", familyId)
            ))) {
                continue;
            }
            lprDAO.insert(record);
        }
        return Constants.OK;
    }


    /**
     * 分配各家电费
     *
     * @param powerCommonUse
     * @param billMonth
     * @param powerBill
     * @param familys
     * @return
     */
    protected Map<Integer, Double> dealPowerBill(Double powerCommonUse, Integer billMonth, Double powerBill, List<LifePayFamilyDO> familys) throws CommonException {
        Map<Integer, Double> powerCostMap = new HashMap<Integer, Double>(3);
//        Double powerCommonUse = 0.0;
        Double powerTotalUse = 0.0;
        powerTotalUse += powerCommonUse;
        Map<Integer, Double> powerUseMap = new HashMap<Integer, Double>(3);
        for (LifePayFamilyDO family : familys) {
            Set<LifePayPowerUseRecordDO> powerUseRecords = family.getPowerUseRecords();
            Integer familyId = family.getFamilyId();
            if (AssertUtils.isSetEmpty(powerUseRecords)) {
                break;
            }
            for (LifePayPowerUseRecordDO powerUseRecord : powerUseRecords) {
                if (billMonth.equals(powerUseRecord.getRecordMonth())) {
                    powerUseMap.put(familyId, powerUseRecord.getPowerUse());
                    powerTotalUse += powerUseRecord.getPowerUse();
                }
            }
        }
        Map<Integer, Double> commonPowerUsedestributeMap = dealCommonPowerUse(powerCommonUse, familys);
        if (AssertUtils.isMapEmpty(commonPowerUsedestributeMap)) {
            //todo 抛出公共用电分配失败异常，生成当月消费记录方法全部回滚
            throw new LifePayException(CommonErrorMessage.LIFEPAY_COMMON_POWER_USE_DESTRIBUTE_FAIL_ERR);
        }
        for (Map.Entry<Integer, Double> entry : powerUseMap.entrySet()) {
            Integer familyId = entry.getKey();
            Double powerUse = entry.getValue();
            Double powerCost = powerBill * (powerUse + commonPowerUsedestributeMap.get(familyId)) / powerTotalUse;
            powerCostMap.put(familyId, powerCost);
        }
        return powerCostMap;
    }

    /**
     * 处理公共用电部分
     * 按照各家庭人头分配
     *
     * @param commonPowerUse
     * @param familys
     * @return
     */
    protected Map<Integer, Double> dealCommonPowerUse(Double commonPowerUse, List<LifePayFamilyDO> familys) throws CommonException {
        Map<Integer, Double> commonPowerUseDestributeMap = new HashMap<Integer, Double>(3);
        Map<Integer, Double> familyUseRateMap = generateFamilyDestributeRate(familys);
        if (AssertUtils.isMapEmpty(familyUseRateMap)) {
            //todo 抛出生成分摊比例异常，生成当月消费记录方法全部回滚
            throw new LifePayException(CommonErrorMessage.LIFEPAY_FAMILY_DESTRIBUTE_RATE_GENERATOR_FAIL_ERR);
        }
        for (Map.Entry<Integer, Double> entry : familyUseRateMap.entrySet()) {
            Integer familyId = entry.getKey();
            Double familyUseRate = entry.getValue();
            if (0 == familyUseRate) {
                //todo 抛出生成分摊比例异常，生成当月消费记录方法全部回滚
                throw new LifePayException(CommonErrorMessage.LIFEPAY_FAMILY_DESTRIBUTE_RATE_GENERATOR_FAIL_ERR);
            }
            Double commonPowerUseAfterDestribute = commonPowerUse * familyUseRate;
            commonPowerUseDestributeMap.put(familyId, commonPowerUseAfterDestribute);
        }
        return commonPowerUseDestributeMap;
    }

    /**
     * 分配各家水费
     *
     * @param waterBill
     * @param familys
     * @return
     */
    protected Map<Integer, Double> dealWaterBill(Double waterBill, List<LifePayFamilyDO> familys) throws CommonException {
        Map<Integer, Double> waterCostMap = new HashMap<Integer, Double>(3);
        Map<Integer, Double> familyUseRateMap = generateFamilyDestributeRate(familys);
        if (AssertUtils.isMapEmpty(familyUseRateMap)) {
            //todo 抛出生成分摊比例异常，生成当月消费记录方法全部回滚
            throw new LifePayException(CommonErrorMessage.LIFEPAY_FAMILY_DESTRIBUTE_RATE_GENERATOR_FAIL_ERR);
        }
        for (Map.Entry<Integer, Double> entry : familyUseRateMap.entrySet()) {
            Integer familyId = entry.getKey();
            Double familyUseRate = entry.getValue();
            if (0 == familyUseRate) {
                //todo 抛出生成分摊比例异常，生成当月消费记录方法全部回滚
                throw new LifePayException(CommonErrorMessage.LIFEPAY_FAMILY_DESTRIBUTE_RATE_GENERATOR_FAIL_ERR);
            }
            Double waterCost = waterBill * familyUseRate;
            waterCostMap.put(familyId, waterCost);
        }

        return waterCostMap;
    }

    /**
     * 根据各家人数生成水费或公共电费的分摊比例
     *
     * @param familys
     * @return
     */
    protected Map<Integer, Double> generateFamilyDestributeRate(List<LifePayFamilyDO> familys) {
        Map<Integer, Double> familyUseRateMap = new HashMap<Integer, Double>(3);
        Map<Integer, Integer> familyMemberNumMap = new HashMap<Integer, Integer>(3);
        Double allFamilyMemberNum = 0.0;
        for (LifePayFamilyDO family : familys) {
            Set<LifePayUserDO> familyMembers = family.getFamilyMembers();
            Integer familyId = family.getFamilyId();
            if (AssertUtils.isSetEmpty(familyMembers)) {
                familyMemberNumMap.put(familyId, 0);
                continue;
            }
            familyMemberNumMap.put(familyId, familyMembers.size());
            allFamilyMemberNum += familyMembers.size();
        }
        for (Map.Entry<Integer, Integer> entry : familyMemberNumMap.entrySet()) {
            Integer familyId = entry.getKey();
            Integer familyMemberNum = entry.getValue();
            if (0 == allFamilyMemberNum) {
                familyUseRateMap.put(entry.getKey(), 0.0);
                continue;
            }
            familyUseRateMap.put(familyId, familyMemberNum / allFamilyMemberNum);
        }
        return familyUseRateMap;
    }

    @DataSource(MasterSlave.SLAVE)
    public List<LifePayRecordDTO> listCostRecordByRecordMonth(LifePayRecordPDTO paramsDTO) throws CommonException {
        List<LifePayRecordDTO> records = new ArrayList<LifePayRecordDTO>();
        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Integer billMonth = paramsDTO.getBillMonth();
        if (null == billMonth) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        List<LifePayRecordDO> recordsRaw = lprDAO.selectList(
                new EntityWrapper<LifePayRecordDO>()
                        .eq("record_month", billMonth)
        );
        if (AssertUtils.isListEmpty(recordsRaw)) {
            //TODO 抛出查询消费记录为空异常
            throw new LifePayException(CommonErrorMessage.LIFEPAY_FAMLILY_COST_RECORD_FETCH_FAIL_ERR);
        }
        for (LifePayRecordDO recordRaw : recordsRaw) {
            LifePayRecordDTO record = new LifePayRecordDTO();
            record.setHousemasterName(recordRaw.getFamily().getHousemasterName());
            record.setPowerCost(recordRaw.getPowerCost());
            record.setWaterCost(recordRaw.getWaterCost());
            record.setRecordMonth(recordRaw.getRecordMonth());
            record.setLastModifyDate(recordRaw.getLastModifyDate().toString());
            records.add(record);
        }
        return records;
    }
}
