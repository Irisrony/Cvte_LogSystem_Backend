package com.cvte.logsystem.db_mysql.exception;

import com.cvte.logsystem.db_mysql.response.ResultCode;
import lombok.Data;

/**
 * @Description TODO
 * @Classname AuthException
 * @Date 2023/7/21 10:43 AM
 * @Created by liushenghao
 */

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
}
