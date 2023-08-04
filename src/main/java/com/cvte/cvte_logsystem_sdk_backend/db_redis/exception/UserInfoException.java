package com.cvte.cvte_logsystem_sdk_backend.db_redis.exception;

import com.cvte.cvte_logsystem_sdk_backend.response.ResultCode;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

/**
 * @Description TODO
 * @Classname UserInfoException
 * @Date 2023/8/4 3:22 PM
 * @Created by liushenghao
 */
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
