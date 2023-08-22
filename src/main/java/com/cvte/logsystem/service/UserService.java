package com.cvte.logsystem.service;

import com.cvte.logsystem.exception.LoginException;
import com.cvte.logsystem.mysql.mapper.UserMapper;
import com.cvte.logsystem.domain.User;
import com.cvte.logsystem.redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem.response.ResultCode;
import com.cvte.logsystem.utils.CookieUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static com.cvte.logsystem.utils.JwtUtils.getToken;

@Service
@Slf4j
public class UserService{
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisRepositoryImpl redisRepository;

    private final static String TOKEN = "token";

    private final static String TOKEN_LIST = "tokenList";

    private final static int EXPIRE_TIME = 24*60*60;

    /**
     * 用户登陆
     * @param user  登陆用户信息实体
     * @param response  响应
     */
    public Boolean login(User user, HttpServletResponse response) {
        try{
            if (user != null) {
                final String username = user.getUsername();
                User u = userMapper.findByName(username);
                // 校验成功
                if (u != null && BCrypt.checkpw(user.getPassword(), u.getPassword())) {
                    String token = getToken(u);
                    CookieUtils.set(response,TOKEN,token,EXPIRE_TIME,true);
                    // 清除当前用户之前的token
                    String pre_token = (String) redisRepository.getValue(username);
                    if(Strings.isNotBlank(pre_token)){
                        redisRepository.removeSetValue(TOKEN_LIST,pre_token);
                    }
                    // 添加当前token
                    redisRepository.setValue(username,token,EXPIRE_TIME*1000);
                    redisRepository.setSetValue(TOKEN_LIST,token);
                    return true;
                }
            }
            throw new LoginException(ResultCode.USER_LOGIN_ERROR);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new LoginException(ResultCode.USER_LOGIN_ERROR);
        }
    }


    /**
     * 用户登出
     * @param response  响应
     */
    public Boolean logout(HttpServletResponse response){
        CookieUtils.set(response,TOKEN,null,0,true);
        return true;
    }

}
