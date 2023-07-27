package com.cvte.logsystem.db_mysql.utils;

import com.cvte.logsystem.db_mysql.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String secret_key = "MY-SECRET";

    /**
     * 获取用户token
     *
     * @param user
     * @return
     */
    public static String getToken(User user) {
        Jwts.builder();
        Jwts.parser();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .setExpiration(new Date(new Date().getTime() + 86400000));

        return jwtBuilder.compact();
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token) {
        if (token == null) return false;
        Date cur = new Date();
        Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
        return claims.getExpiration().after(cur);
    }
}
