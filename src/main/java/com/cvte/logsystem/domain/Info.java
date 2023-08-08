package com.cvte.logsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Classname Info
 * @Date 2023/8/4 3:35 PM
 * @Created by liushenghao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private Integer type;
    private Long timestamp;
    private String msg;
}
