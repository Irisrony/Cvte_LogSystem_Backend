package com.cvte.logsystem_sdk.exception;

import com.cvte.logsystem_sdk.response.ResultCode;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class UserInfoException extends RuntimeException{
    private String msg;
    private String code;

    public UserInfoException(ResultCode code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public UserInfoException(ResultCode code,String msg){
        this.code = code.getCode();
        this.msg = Strings.isNotBlank(msg) ? msg : code.getMsg();
    }

    @Override
    public String toString() {
        return "UserInfoException{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
