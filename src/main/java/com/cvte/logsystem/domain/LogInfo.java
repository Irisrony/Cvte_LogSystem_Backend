package com.cvte.logsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

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
}
