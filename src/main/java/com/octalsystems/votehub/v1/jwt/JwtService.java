package com.octalsystems.votehub.v1.jwt;

import com.octalsystems.votehub.v1.dto.LoginResponseDTO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class JwtService {

    private static String JWT_BEARER = "Bearer ";
    private static String JWT_AUTHORIZATION = "Authorization";

    @Value("${jwt.secret}")
    private final String secretKey;

    public LoginResponseDTO generateToken(String email, String role, String id, String issuer, long ttlMillis) {
        MacAlgorithm signatureAlgorithm = Jwts.SIG.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date limit = new Date();

        SecretKey key = Keys.hmacShaKeyFor(getSecretKey().getBytes(StandardCharsets.UTF_8));

        JwtBuilder token = Jwts.builder()
                .header().add("typ","JWT").and()
                .id(id)
                .issuedAt(now)
                .expiration(limit)
                .subject(email)
                .issuer(issuer)
                .claim("role", role)
                .signWith(key, signatureAlgorithm);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            token.expiration(exp);
        }

        return new LoginResponseDTO(token.compact());
    }

}
