package com.rabbit.java.service;


import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.domain.dto.LifePayRecordDTO;
import com.rabbit.java.domain.dto.params.LifePayRecordPDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author Mr.Rabbit
 */
public interface LifePayRecordService {

    /**
     * 生成当月的消费记录
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    Object generateCostRecordByRecordMonth(LifePayRecordPDTO paramsDTO) throws CommonException;

    /**
     * 拉取所有家庭当月消费记录
     *
     * @param paramsDTO
     * @return
     * @throws CommonException
     */
    @Cacheable(value = "lifePayRecordCache", key = "#paramsDTO.billMonth")
    List<LifePayRecordDTO> listCostRecordByRecordMonth(LifePayRecordPDTO paramsDTO) throws CommonException;


    /**
     * 删除消费记录
     *
     * @param paramsDTO
     */
    @CacheEvict(value = "lifePayRecordCache", key = "#paramsDTO.billMonth")
    void removeCostRecord(LifePayRecordPDTO paramsDTO);


}
