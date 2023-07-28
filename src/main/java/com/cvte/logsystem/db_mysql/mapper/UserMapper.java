package com.cvte.logsystem.db_mysql.mapper;

import com.cvte.logsystem.db_mysql.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 根据用户名查找
    User findByName(String username);

    // 添加管理员
    int addUser(User user);
}
