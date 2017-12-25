package com.rabbit.java.service;


import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.domain.dto.LifePayUserDTO;
import com.rabbit.java.domain.dto.params.LifePayUserPDTO;
import com.rabbit.java.domain.model.LifePayFamilyDO;

public interface LifePayUserService {

    /**
     * 创建家庭单位
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    LifePayUserDTO createFamily(LifePayUserPDTO paramsDTO) throws CommonException;

    /**
     * 更新家庭信息
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    LifePayUserDTO updateFamilyInfo(LifePayUserPDTO paramsDTO) throws CommonException;


    /**
     * 添加家庭成员
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    Object addFamilyMember(LifePayUserPDTO paramsDTO) throws CommonException;


    /**
     * 录入当月每个家庭用电情况
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    Object recordPowerUseSituation(LifePayUserPDTO paramsDTO) throws CommonException;


    /**
     * 获取家庭信息
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    LifePayFamilyDO findByFamilyId(LifePayUserPDTO paramsDTO) throws CommonException;


}
