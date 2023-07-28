package com.cvte.logsystem.db_mysql.mapper;

import com.cvte.logsystem.db_mysql.domain.AppInfo;
import com.cvte.logsystem.db_mysql.domain.UploadEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description TODO
 * @Classname AppInfoMapper
 * @Date 2023/7/28 11:30 AM
 * @Created by liushenghao
 */
@Mapper
public interface AppInfoMapper {
    public int addAppInfo(AppInfo appInfo);
    public int checkExist(String appid);
    public int addUploadData(UploadEntity uploadEntity);
}
