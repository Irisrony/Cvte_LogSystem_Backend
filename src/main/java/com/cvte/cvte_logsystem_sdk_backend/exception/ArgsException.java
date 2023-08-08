package com.cvte.cvte_logsystem_sdk_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Classname ArgsException
 * @Date 2023/8/7 6:44 PM
 * @Created by liushenghao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArgsException extends RuntimeException{
    private String code;
    private String msg;
}
