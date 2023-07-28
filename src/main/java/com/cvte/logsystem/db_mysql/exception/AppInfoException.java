package com.cvte.logsystem.db_mysql.exception;

import com.cvte.logsystem.db_mysql.response.ResultCode;
import lombok.Data;

/**
 * @Description TODO
 * @Classname AppInfoException
 * @Date 2023/7/28 2:26 PM
 * @Created by liushenghao
 */
@Data
public class AppInfoException extends RuntimeException{
    private String code;
    private String msg;

    public AppInfoException(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public AppInfoException(ResultCode code,String msg) {
        this.code = code.getCode();
        this.msg = msg == null ? code.getMsg() : msg;
    }

    @Override
    public String toString() {
        return "AppInfoException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
