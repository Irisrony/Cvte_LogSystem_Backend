package com.cvte.logsystem.utils;

import com.cvte.logsystem.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final static String secret_key = "MY-SECRET";
    private final static int EXPIRE_TIME = 86400000;

    /**
     * 生成用户token
     * @param user  用户信息
     * @return  token
     */
    public static String getToken(User user) {
        Jwts.builder();
        Jwts.parser();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME));

        return jwtBuilder.compact();
    }

    /**
     * 验证token
     * @param token token
     * @return  验证结果
     */
    public static Boolean verifyToken(String token) {
        if (token == null) return false;
        Date cur = new Date();
        Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
        return claims.getExpiration().after(cur);
    }
}
