package com.rabbit.java.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rabbit.java.common.annotation.DataSource;
import com.rabbit.java.common.constant.Constants;
import com.rabbit.java.common.enums.MasterSlave;
import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.result.CommonErrorMessage;
import com.rabbit.java.common.util.AssertUtils;
import com.rabbit.java.dao.LifePayBillDAO;
import com.rabbit.java.domain.dto.LifePayBillDTO;
import com.rabbit.java.domain.dto.params.LifePayBillPDTO;
import com.rabbit.java.domain.model.LifePayBillDO;
import com.rabbit.java.service.LifePayBillService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.Rabbit
 */
@Service
public class LifePayBillServiceImpl implements LifePayBillService {
    @Autowired
    LifePayBillDAO lpbDAO;

    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public LifePayBillDTO pullBill(LifePayBillPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        LifePayBillDO billAR = paramsDTO.getBill();
        if (null == billAR) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        billAR.insert();

//        LifePayBillPDTO lifePayBillPDTO = new LifePayBillPDTO();
//        LifePayBillDO lifePayBillDO = new LifePayBillDO();
//        lifePayBillDO.setBillId(2);
//        lifePayBillDO.setRecordMonth(3);
//        lifePayBillPDTO.setBill(lifePayBillDO);
//        LifePayBillService lifePayBillService = (LifePayBillService) AopContext.currentProxy();
//        try {
//            lifePayBillService.updateBill(lifePayBillPDTO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//            int i = 1 / 0;
        LifePayBillDTO result = new LifePayBillDTO();
        result.setPowerBill(billAR.getPowerBill());
        result.setPowerRate(billAR.getPowerRate());
        result.setWaterBill(billAR.getWaterBill());
        result.setWaterRate(billAR.getWaterRate());
        result.setLastModifyDate(billAR.getLastModifyDate().toString());
        result.setRecordMonth(billAR.getRecordMonth());

        return result;
    }

    @DataSource(MasterSlave.MASTER)
    public Object deleteBill(LifePayBillPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Integer billId = paramsDTO.getBillId();
        if (null == billId) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        Integer deleteFeedBack = lpbDAO.deleteById(billId);
        if (1 != deleteFeedBack) {
            throw new CommonException(CommonErrorMessage.LIFEPAY_BILL_NOT_EXIST_ERR);
        }
        return Constants.OK;
    }

    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public Object updateBill(LifePayBillPDTO paramsDTO) throws CommonException {

        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        LifePayBillDO bill = paramsDTO.getBill();
        if (null == bill) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        if (!bill.updateById()) {
            throw new CommonException(CommonErrorMessage.LIFEPAY_BILL_UPDATE_ERR);
        }
//        Integer updateFeedBack = lpbDAO.updateById(bill);
//        if (1 != updateFeedBack) {
//            throw new CommonException(CommonErrorMessage.LIFEPAY_BILL_UPDATE_ERR);
//        }
//        int i = 1 / 0;
        return Constants.OK;
    }

    @DataSource(MasterSlave.SLAVE)
    public LifePayBillDTO findByBillMonth(LifePayBillPDTO paramsDTO) throws CommonException {

        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Integer billMonth = paramsDTO.getBillMonth();
        if (null == billMonth) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
//        LifePayBillDOExample lifePayBillDOExample = new LifePayBillDOExample();
//        LifePayBillDOExample.Criteria criteria = lifePayBillDOExample.createCriteria();
//        criteria.andRecordMonthEqualTo(billMonth);
        LifePayBillDO billAR = new LifePayBillDO();
        List<LifePayBillDO> bills = billAR.selectList(
                new EntityWrapper<LifePayBillDO>().eq("record_month", billMonth)
        );
//        List<LifePayBillDO> bills = lpbDAO.selectList(
//                new EntityWrapper<LifePayBillDO>().eq("record_month", billMonth)
//        );
        if (AssertUtils.isListEmpty(bills)) {
            throw new CommonException(CommonErrorMessage.LIFEPAY_BILL_NOT_EXIST_ERR);
        }
        LifePayBillDO bill = bills.get(0);
        if (null == bill) {
            throw new CommonException(CommonErrorMessage.LIFEPAY_BILL_NOT_EXIST_ERR);
        }
        LifePayBillDTO result = new LifePayBillDTO();
        result.setPowerBill(bill.getPowerBill());
        result.setPowerRate(bill.getPowerRate());
        result.setWaterBill(bill.getWaterBill());
        result.setWaterRate(bill.getWaterRate());
        result.setRecordMonth(bill.getRecordMonth());
        return result;
    }
}
