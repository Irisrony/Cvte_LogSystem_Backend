package com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Classname Info
 * @Date 2023/8/3 10:46 AM
 * @Created by liushenghao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private int type;
    private long timestamp;
    private String msg;
}
