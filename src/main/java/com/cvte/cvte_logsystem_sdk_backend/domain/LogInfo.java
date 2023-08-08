package com.cvte.cvte_logsystem_sdk_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

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
    private ObjectId id;
    private String appid;
    private String userid;
    private Info info;
}
