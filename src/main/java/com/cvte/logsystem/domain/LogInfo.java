package com.cvte.logsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.LinkedList;

/**
 * @Description TODO
 * @Classname LogInfo
 * @Date 2023/8/2 9:13 AM
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
}
