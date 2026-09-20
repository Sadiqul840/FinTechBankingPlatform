package com.sadiq.fintech.auth.security;

import com.sadiq.fintech.auth.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtServiceTest {

    @Test
    public void shouldGenerateValidToken() {

        JwtProperties jwtProperties = new JwtProperties();

        jwtProperties.setSecret(
                "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY="
        );
        jwtProperties.setExpiration(3600000);

        JwtService jwtService = new JwtService(jwtProperties);

        User user = new User();
        user.setUserId(1L);
        user.setUsername("imroy");
        user.setRole("CUSTOMER");

        String token = jwtService.generateToken(user);
        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token));
        assertEquals("imroy", jwtService.extractUsername(token));
        String tamperedToken = token + "abc";

        assertFalse(jwtService.isTokenValid(tamperedToken));
    }

    @Test
     public void shouldRejectExpiredToken() {
        JwtProperties jwtProperties = new JwtProperties();

        jwtProperties.setSecret(
                "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY="
        );

        jwtProperties.setExpiration(-1000);

        JwtService jwtService = new JwtService(jwtProperties);

        User user = new User();
        user.setUserId(1L);
        user.setUsername("imroy");
        user.setRole("CUSTOMER");

        String token = jwtService.generateToken(user);

        assertFalse(jwtService.isTokenValid(token));

    }
}
