package com.rabbit.java.common.result;

public class CommonErrorMessage {
    /**
     * 通用
     */
    public static final CommonErrorMessage INTERFACE_NO_PARAMS_ERR = new CommonErrorMessage("接口无参数传入", -101);
    public static final CommonErrorMessage INTERFACE_NO_PARAMS_NEEDED_ERR = new CommonErrorMessage("接口指定参数缺失", -102);
    /**
     * lifepay项目
     */
    public static final CommonErrorMessage LIFEPAY_NO_BILL_GENERATOR_ERR = new CommonErrorMessage("当月账单未生成", -201);
    public static final CommonErrorMessage LIFEPAY_NO_FAMILY_ERR = new CommonErrorMessage("找不到需要记账的家庭", -202);
    public static final CommonErrorMessage LIFEPAY_NO_COMMON_POWER_USE_RECORD_ERR = new CommonErrorMessage("找不到当月公用用电记录", -203);
    public static final CommonErrorMessage LIFEPAY_COMMON_POWER_USE_DESTRIBUTE_FAIL_ERR = new CommonErrorMessage("公共用电分配失败", -204);
    public static final CommonErrorMessage LIFEPAY_FAMILY_DESTRIBUTE_RATE_GENERATOR_FAIL_ERR = new CommonErrorMessage("生成各家庭分摊比例异常", -205);
    public static final CommonErrorMessage LIFEPAY_FAMLILY_COST_RECORD_FETCH_FAIL_ERR = new CommonErrorMessage("查询不到当月各家账单记录", -206);
    public static final CommonErrorMessage LIFEPAY_BILL_NOT_EXIST_ERR = new CommonErrorMessage("账单信息不存在", -207);
    public static final CommonErrorMessage LIFEPAY_BILL_UPDATE_ERR = new CommonErrorMessage("账单信息更新出错", -208);
    public static final CommonErrorMessage LIFEPAY_FAMILY_INFO_NOT_EXIST_ERR = new CommonErrorMessage("找不到指定家庭信息", -209);


    private String msg;
    private int code;

    public CommonErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
