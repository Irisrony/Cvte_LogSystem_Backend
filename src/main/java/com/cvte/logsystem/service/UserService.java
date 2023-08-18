package com.cvte.logsystem.service;

import com.cvte.logsystem.mysql.mapper.UserMapper;
import com.cvte.logsystem.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cvte.logsystem.utils.JwtUtils.getToken;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登陆
     * @param user  用户信息
     * @return  token
     */
    public String login(User user) {
        String token = null;
        if (user != null) {
            User u = userMapper.findByName(user.getUsername());
            if (u != null && BCrypt.checkpw(user.getPassword(), u.getPassword())) {
                token = getToken(u);
            }
        }
        return token;
    }

    // 管理员注册
    public Boolean register(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        user.setRole(0);
        return userMapper.addUser(user) == 1;
    }
}
