package com.cvte.logsystem.db_mysql.mapper;

import com.cvte.logsystem.domain.AppInfo;
import com.cvte.logsystem.domain.UploadEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    public List<AppInfo> getAllAppInfo();
}
