package com.cvte.logsystem_sdk.response;

/**
 * @Description TODO
 * @Classname ResultCode
 * @Date 2023/8/2 5:00 PM
 * @Created by liushenghao
 */
public enum ResultCode {
    SUCCESS("00000", "成功"),
    MISSING_PARAM("A0100", "缺少参数"),
    UNSUPPORTED_METHOD("A0101", "不支持的请求类型"),
    SYSTEM_ERROR("A0102", "系统错误"),
    VALIDATION_FAILED("A0103","参数校验失败"),
    APPID_NOT_EXIST("A0104","appid不存在"),
    UNAUTHORIZED("A0105","用户无需上传");
    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
