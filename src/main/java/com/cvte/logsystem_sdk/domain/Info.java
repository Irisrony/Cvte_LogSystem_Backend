package com.cvte.logsystem_sdk.domain;

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

    @Override
    public String toString() {
        return "Info{" + "type=" + type + ", timestamp=" + timestamp + ", msg='" + msg + '\'' + '}';
    }
}
