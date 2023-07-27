package com.cvte.logsystem.db_mysql.response;

import lombok.Data;

@Data
public class ResultVo {
    private String code;
    private String msg;
    private Object data;
}
