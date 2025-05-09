package com.kiki.newsbe.configs.security;

import com.kiki.newsbe.configs.constant.ConstantHeader;
import com.kiki.newsbe.configs.constant.ConstantSecurity;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final String secret = ConstantSecurity.SECRET;
    private final long expirationTime = ConstantSecurity.EXPIRATION_TIME;
    private final long refreshExpirationTime = ConstantSecurity.REFRESH_EXPIRATION_TIME;
    private final String X_WHO = ConstantHeader.HEADER_X_WHO;
    private final String X_ROLE = ConstantHeader.HEADER_X_ROLE;

    // Generate Access Token
    public String generateAccessToken(UserEntity user) {
        return JWT.create()
                .withSubject(user.getUser_email())
                .withClaim("id", user.getId())
                .withClaim("user_name", user.getUser_name())
                .withClaim("role", user.getRole().getName().name())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
    }

    // Generate Refresh Token
    public String generateRefreshToken(UserEntity user) {
        return JWT.create()
                .withSubject(user.getUser_email())
                .withClaim("id", user.getId())
                .withClaim("token_type", "refresh")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpirationTime))
                .sign(Algorithm.HMAC256(secret));
    }

    // Validate JWT Token
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getClaim(String token, String claim) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getClaim(claim)
                .asString();
    }

    public boolean isRefreshToken(String token) {
        try {
            String tokenType = JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token)
                    .getClaim("token_type")
                    .asString();
            return "refresh".equals(tokenType);
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}


