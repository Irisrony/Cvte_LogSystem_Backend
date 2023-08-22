package com.cvte.logsystem.mysql.mapper;

import com.cvte.logsystem.domain.AppInfo;
import com.cvte.logsystem.domain.UploadEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppInfoMapper {
    int addAppInfo(AppInfo appInfo);
    int checkExist(String appid);
    int addUploadData(UploadEntity uploadEntity);
    List<AppInfo> getAllAppInfo();
}
