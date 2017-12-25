package com.rabbit.java.controller.lifepay;


import com.rabbit.java.common.constant.UTF8MediaType;
import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.result.CommonResult;
import com.rabbit.java.domain.dto.params.LifePayUserPDTO;
import com.rabbit.java.domain.model.LifePayFamilyDO;
import com.rabbit.java.service.LifePayUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("lifepay/user")
public class LifePayUser4ClientController {

    @Autowired
    LifePayUserService lpuService;

    @RequestMapping(value = "/createFamily", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult createFamily(@RequestBody LifePayUserPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpuService.createFamily(paramsDTO));
        return result;
    }


    @RequestMapping(value = "/addFamilyMember", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult addFamilyMember(@RequestBody LifePayUserPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpuService.addFamilyMember(paramsDTO));
        return result;
    }


    @RequestMapping(value = "/recordPowerUse", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult recordPowerUse(@RequestBody LifePayUserPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpuService.recordPowerUseSituation(paramsDTO));
        return result;
    }

    @RequestMapping(value = "/findFamily", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult findFamily(@RequestBody LifePayUserPDTO paramsDTO) throws CommonException {
        LifePayFamilyDO family = lpuService.findByFamilyId(paramsDTO);
        CommonResult result = CommonResult.success(family);
        return result;
    }
}
