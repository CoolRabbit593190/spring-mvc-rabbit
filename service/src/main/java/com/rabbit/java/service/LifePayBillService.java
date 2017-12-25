package com.rabbit.java.service;


import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.domain.dto.LifePayBillDTO;
import com.rabbit.java.domain.dto.params.LifePayBillPDTO;
import org.springframework.cache.annotation.Cacheable;



/**
 * @author mr.rabbit
 */
public interface LifePayBillService {

    /**
     * 拉取消费账单
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    LifePayBillDTO pullBill(LifePayBillPDTO paramsDTO) throws CommonException;

    /**
     * 删除账单
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    Object deleteBill(LifePayBillPDTO paramsDTO) throws CommonException;

    /**
     * 更新账单
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    Object updateBill(LifePayBillPDTO paramsDTO) throws CommonException;

    /**
     * 查询每月账单
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    @Cacheable(value = "lifePayBillCache", key = "#paramsDTO.billMonth")
    LifePayBillDTO findByBillMonth(LifePayBillPDTO paramsDTO) throws CommonException;


}
