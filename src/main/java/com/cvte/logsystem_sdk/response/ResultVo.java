package com.cvte.logsystem_sdk.response;

import lombok.Data;

@Data
public class ResultVo {
    private String code;
    private String msg;
    private Object data;
}
