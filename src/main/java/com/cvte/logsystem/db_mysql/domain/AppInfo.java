package com.cvte.logsystem.db_mysql.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Description TODO
 * @Classname App
 * @Date 2023/7/28 11:23 AM
 * @Created by liushenghao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppInfo {
    private String appid;
    @NotBlank
    @Length(min = 4,max = 50)
    private String appName;
}
