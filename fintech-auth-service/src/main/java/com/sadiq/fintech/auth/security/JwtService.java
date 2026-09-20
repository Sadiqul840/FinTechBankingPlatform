package com.sadiq.fintech.auth.security;

import com.sadiq.fintech.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    final private JwtProperties jwtProperties;
    final private SecretKey secretKey;

    public  JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecret())
        );
    }


    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getUserId())
                .claim("userRole",user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(secretKey)
                .compact();
    }


    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return  true;
        } catch(Exception e){
            return  false;
        }
    }



}
