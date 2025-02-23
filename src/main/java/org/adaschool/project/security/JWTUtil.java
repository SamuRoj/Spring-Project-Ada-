package org.adaschool.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.adaschool.project.controller.auth.TokenDTO;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JWTUtil {

    private final JWTConfig jwtConfig;

    public JWTUtil(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public TokenDTO generateToken(String username) {
        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(jwtConfig.getSigningKey())
                .compact();
        return new TokenDTO(token, expirationDate);
    }

    public Claims extractAndVerifyClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
