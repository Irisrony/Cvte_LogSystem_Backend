package com.cvte.logsystem_sdk.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfo {
    @NotBlank
    private String appid;
    @NotBlank
    private String userid;
}
