package com.cvte.logsystem_sdk.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description TODO
 * @Classname UserInfo
 * @Date 2023/8/2 4:17 PM
 * @Created by liushenghao
 */
@Data
public class UserInfo {
    @NotBlank
    private String appid;
    @NotBlank
    private String userid;
}
