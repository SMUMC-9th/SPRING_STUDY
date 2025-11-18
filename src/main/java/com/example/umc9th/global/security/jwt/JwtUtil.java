package com.example.umc9th.global.security.jwt;

import com.example.umc9th.domain.Member.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;
    private final Duration refreshExpiration;

    private JwtUtil(
            @Value("${Jwt.secret}") String secret,
            @Value("${Jwt.time.access-expiration}") long accessExpiration,
            @Value("${Jwt.time.refresh-expiration}") long refreshExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
        this.refreshExpiration = Duration.ofMillis(refreshExpiration);
    }

    public String createAccessToken(Member member){
        return createToken(member, accessExpiration);
    }

    public String createRefreshToken(Member member){
        return createToken(member, refreshExpiration);
    }

    public String getUsername(String token) {
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean isValid(String token) {
        try{
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"));
    }

    private String createToken(Member member, Duration expiration) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(member.getUsername())
                .claim("id", member.getId())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
