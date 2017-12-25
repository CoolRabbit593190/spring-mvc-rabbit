package com.rabbit.java.service.impl;


import com.rabbit.java.common.annotation.DataSource;
import com.rabbit.java.common.constant.Constants;
import com.rabbit.java.common.enums.MasterSlave;
import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.result.CommonErrorMessage;
import com.rabbit.java.common.util.AssertUtils;
import com.rabbit.java.dao.LifePayFamilyDAO;
import com.rabbit.java.dao.LifePayPowerUseRecordDAO;
import com.rabbit.java.dao.LifePayUserDAO;
import com.rabbit.java.domain.dto.LifePayUserDTO;
import com.rabbit.java.domain.dto.params.LifePayUserPDTO;
import com.rabbit.java.domain.model.LifePayFamilyDO;
import com.rabbit.java.domain.model.LifePayPowerUseRecordDO;
import com.rabbit.java.domain.model.LifePayUserDO;
import com.rabbit.java.service.LifePayUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class LifePayUserServiceImpl implements LifePayUserService {
    @Autowired
    LifePayFamilyDAO llfDAO;
    @Autowired
    LifePayUserDAO lpuDAO;
    @Autowired
    LifePayPowerUseRecordDAO lppurDAO;


    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public LifePayUserDTO createFamily(LifePayUserPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        LifePayFamilyDO familyAR = paramsDTO.getFamily();
        if (null == familyAR) {
            //todo 抛出持久化单元为空异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        familyAR.insert();
//        llfDAO.insert(familyAR);
//        LifePayFamily familyP = llfRepository.save(familyT);
        LifePayUserDTO result = new LifePayUserDTO();
        result.setFamilyId(familyAR.getFamilyId());
        return result;
    }


    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public LifePayUserDTO updateFamilyInfo(LifePayUserPDTO paramsDTO) throws CommonException {
        return null;
    }

    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public Object addFamilyMember(LifePayUserPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            //todo 抛出方法参数为空异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Set<LifePayUserDO> familyMembers = paramsDTO.getFamilyMembers();
        Integer familyId = paramsDTO.getFamilyId();
        if (null == familyId) {
            //todo 抛出未选择家庭异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        if (AssertUtils.isSetEmpty(familyMembers)) {
            //todo 抛出未选择家庭成员异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        for (LifePayUserDO familyMemberAR : familyMembers) {
            familyMemberAR.setFamilyId(familyId);
            familyMemberAR.insert();
        }
        return Constants.OK;
    }

    @DataSource(MasterSlave.MASTER)
    @Transactional(transactionManager = "tm4mybatis", rollbackFor = Exception.class)
    public Object recordPowerUseSituation(LifePayUserPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            //todo 抛出方法参数为空异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        LifePayPowerUseRecordDO powerUseRecordAR = paramsDTO.getPowerUseRecord();
        if (null == powerUseRecordAR) {
            //todo 抛出未输入用电记录异常
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        Integer familyId = paramsDTO.getFamilyId();
        /**
         * 针对公共用电记录不绑定任何家庭
         * */
        if (null == familyId) {
            powerUseRecordAR.insert();
            return Constants.OK;
        }
        powerUseRecordAR.setFamilyId(familyId);
        powerUseRecordAR.insert();
        return Constants.OK;
    }


    @DataSource(MasterSlave.SLAVE)
    public LifePayFamilyDO findByFamilyId(LifePayUserPDTO paramsDTO) throws CommonException {
        if (null == paramsDTO) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_ERR);
        }
        Integer familyId = paramsDTO.getFamilyId();
        if (null == familyId) {
            throw new CommonException(CommonErrorMessage.INTERFACE_NO_PARAMS_NEEDED_ERR);
        }
        LifePayFamilyDO family = llfDAO.selectById(familyId);
        if (null == family) {
            throw new CommonException(CommonErrorMessage.LIFEPAY_FAMILY_INFO_NOT_EXIST_ERR);
        }
//        Hibernate.initialize(family.getFamilyMembers());
//        Hibernate.initialize(family.getPowerUseRecords());
//        Hibernate.initialize(family.getRecords());
        return family;
    }
}
