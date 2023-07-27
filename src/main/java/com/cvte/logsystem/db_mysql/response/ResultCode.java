package com.cvte.logsystem.db_mysql.response;

public enum ResultCode {
    SUCCESS("00000", "成功"),
    USER_LOGIN_ERROR("A0100", "用户名或密码错误"),
    UNAUTHORIZED("A0101", "token非法"),
    MISSING_PARAM("A0102", "缺少参数"),
    UNSUPPORTED_METHOD("A0103", "不支持的请求类型"),
    SYSTEM_ERROR("A0104", "系统错误"),
    ACCOUNT_EXIST("A0105", "账号已存在");

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
