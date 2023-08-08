package com.cvte.logsystem_sdk.exception;

import com.cvte.logsystem_sdk.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Classname ArgsException
 * @Date 2023/8/7 6:44 PM
 * @Created by liushenghao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArgsException extends RuntimeException{
    private String code;
    private String msg;

    public ArgsException(ResultCode code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public String getMessage(){
        return toString();
    }
}
