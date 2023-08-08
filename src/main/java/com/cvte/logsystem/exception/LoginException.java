package com.cvte.logsystem.exception;

import com.cvte.logsystem.response.ResultCode;
import lombok.Data;

@Data
public class LoginException extends RuntimeException {
    private String code;
    private String msg;

    public LoginException(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public LoginException(ResultCode code,String msg) {
        this.code = code.getCode();
        this.msg = msg == null ? code.getMsg() : msg;
    }

    @Override
    public String toString() {
        return "LoginException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
