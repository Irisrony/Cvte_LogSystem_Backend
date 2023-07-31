package com.cvte.logsystem.db_mysql.domain;

import com.cvte.logsystem.db_mysql.service.AppInfoService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

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
    private Set<Object> userid;
    public AppInfo(String appid,String appName){
        this.appid = appid;
        this.appName = appName;
    }
}
