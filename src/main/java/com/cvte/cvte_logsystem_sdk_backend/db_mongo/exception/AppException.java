package com.cvte.cvte_logsystem_sdk_backend.db_mongo.exception;

import com.cvte.cvte_logsystem_sdk_backend.response.ResultCode;
import lombok.Data;

/**
 * @Description TODO
 * @Classname AppException
 * @Date 2023/8/4 2:55 PM
 * @Created by liushenghao
 */
@Data
public class AppException extends RuntimeException{
    private String code;
    private String msg;

    public AppException(ResultCode code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public AppException(ResultCode code,String msg){
        this.code = code.getCode();
        this.msg = msg == null ? code.getMsg() : msg;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

