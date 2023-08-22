package com.cvte.logsystem_sdk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private int type;
    private long timestamp;
    private String msg;

    private LinkedHashMap extra;
}
