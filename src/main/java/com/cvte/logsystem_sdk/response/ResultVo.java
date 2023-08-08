package com.cvte.logsystem_sdk.response;

import lombok.Data;

/**
 * @Description TODO
 * @Classname ResultVo
 * @Date 2023/8/2 4:58 PM
 * @Created by liushenghao
 */
@Data
public class ResultVo {
    private String code;
    private String msg;
    private Object data;
}
