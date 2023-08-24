package com.cvte.logsystem.exception;

import com.cvte.logsystem.response.ResultCode;
import lombok.Data;

@Data
public class AuthException extends RuntimeException {
    private String code;
    private String msg;

    public AuthException(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public AuthException(ResultCode code, String msg){
        this.code = code.getCode();
        this.msg = msg == null ? code.getMsg() : msg;
    }

    @Override
    public String toString() {
        return "AuthException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public String getMessage(){
        return toString();
    }
}
