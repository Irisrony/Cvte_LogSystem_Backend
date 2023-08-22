package com.cvte.logsystem;

import com.cvte.logsystem.domain.User;
import com.cvte.logsystem.mysql.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void userLoginTest(){

        User testUser = new User();
        User u;

        // 1. 错误测试
        // 1.1 错误用户名，正确密码
        testUser.setUsername("admin111");
        testUser.setPassword("admin111");
        u = userMapper.findByName(testUser.getUsername());
        if (u == null) {
            System.out.println("Login test 1.1 passed!");
        }else{
            System.out.println("Login test 1.1 failed!");
        }

        // 1.2 正确用户名，错误密码
        testUser.setUsername("admin1");
        testUser.setPassword("admin1");
        u = userMapper.findByName(testUser.getUsername());
        if (u != null && !BCrypt.checkpw(testUser.getPassword(), u.getPassword())) {
            System.out.println("Login test 1.2 passed!");
        }else{
            System.out.println("Login test 1.2 failed!");
        }

        // 1.3 错误用户名，错误密码
        testUser.setUsername("admin111");
        testUser.setPassword("admin1");
        u = userMapper.findByName(testUser.getUsername());
        if (u == null) {
            System.out.println("Login test 1.3 passed!");
        }else{
            System.out.println("Login test 1.3 failed!");
        }


        // 1.4 sql注入
        testUser.setUsername("'admin or '1' = '1'");
        testUser.setPassword(BCrypt.hashpw("'admin or '1' = '1'",BCrypt.gensalt()));
        u = userMapper.findByName(testUser.getUsername());
        if (u == null) {
            System.out.println("Login test 1.4 passed!");
        }else{
            System.out.println("Login test 1.4 failed!");
        }

    }

}
