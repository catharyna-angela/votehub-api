package com.octalsystems.votehub.v1.jwt;

import com.octalsystems.votehub.v1.dto.authentication.LoginResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtService {
    private static final long EXPIRATION_MILLIS = 600000L;
    private static final MacAlgorithm signatureAlgorithm = Jwts.SIG.HS256;
    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public LoginResponseDTO generateToken(String role, String id, String email, String issuer) {
        Date now = new Date();
        Date limit = new Date(now.getTime() + EXPIRATION_MILLIS);

        String token = Jwts.builder()
                .header().add("typ", "JWT").and()
                .id(UUID.randomUUID().toString())
                .issuedAt(now)
                .expiration(limit)
                .subject(id)
                .issuer(issuer)
                .claim("role", role)
                .claim("email", email)
                .signWith(secretKey, signatureAlgorithm)
                .compact();

        return new LoginResponseDTO(token, "Bearer", limit.getTime());
    }

}
