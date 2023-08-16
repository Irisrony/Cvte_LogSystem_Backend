package com.cvte.logsystem_sdk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * @Description TODO
 * @Classname LogInfo
 * @Date 2023/8/2 3:40 PM
 * @Created by liushenghao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo {
    private String appid;
    private String userid;

    private int type;
    private long timestamp;
    private String msg;
    private LinkedHashMap extra;

    public LogInfo(String appid,String userid,Info info){
        this.appid = appid;
        this.userid = userid;
        this.type = info.getType();
        this.timestamp = info.getTimestamp();
        this.msg = info.getMsg();
        this.extra = info.getExtra();
    }

    //public Info getInfo(){
    //    return new Info(type,timestamp,msg,extra);
    //}
}
