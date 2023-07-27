package com.cvte.logsystem.db_mysql.exception;

import com.cvte.logsystem.db_mysql.response.ResultCode;
import lombok.Data;

@Data
public class LoginException extends RuntimeException {
    private String code;
    private String msg;

    public LoginException(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
