package com.rabbit.java.controller.lifepay;


import com.rabbit.java.common.annotation.EXHandle;
import com.rabbit.java.common.constant.UTF8MediaType;
import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.result.CommonResult;
import com.rabbit.java.domain.dto.params.LifePayBillPDTO;
import com.rabbit.java.service.LifePayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Rabbit
 */
@RestController
@RequestMapping("lifepay/bill")
public class LifePayBill4ClientController {

    @Autowired
    LifePayBillService lpbService;

    @EXHandle
    @RequestMapping(value = "/pullBill", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult pullBill(@RequestBody LifePayBillPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpbService.pullBill(paramsDTO));
        return result;
    }

    @EXHandle
    @RequestMapping(value = "/deleteBill", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult deleteBill(@RequestBody LifePayBillPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpbService.deleteBill(paramsDTO));
        return result;
    }


    @EXHandle
    @RequestMapping(value = "/updateBill", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult updateBill(@RequestBody LifePayBillPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpbService.updateBill(paramsDTO));
        return result;
    }

    @EXHandle
    @RequestMapping(value = "/getBill", method = RequestMethod.POST, consumes = UTF8MediaType.JSON, produces = UTF8MediaType.JSON)
    public CommonResult getBill(@RequestBody LifePayBillPDTO paramsDTO) throws CommonException {
        CommonResult result = CommonResult.success(lpbService.findByBillMonth(paramsDTO));
        return result;
    }
}
