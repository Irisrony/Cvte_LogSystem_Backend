package com.cvte.logsystem.db_mysql.service;

import com.cvte.logsystem.db_mysql.mapper.UserMapper;
import com.cvte.logsystem.db_mysql.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.cvte.logsystem.db_mysql.utils.JwtUtils.getToken;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    // 管理员登陆
    public Map<String, String> login(User user) {
        Map<String, String> res = new HashMap<>();
        if (user != null) {
            User u = userMapper.findByName(user.getUsername());
            if (u != null && BCrypt.checkpw(user.getPassword(), u.getPassword())) {
                res.put("token", getToken(u));
            }
        }
        return res;
    }

    // 管理员注册
    public String register(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        user.setRole(0);
        if (userMapper.addUser(user) == 1){
            return "注册成功";
        }
        return "注册失败";
    }
}
