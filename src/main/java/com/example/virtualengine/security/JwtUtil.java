package com.example.virtualengine.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long expirationMs;
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil(@Value("${jwt.secret:}") String secret, @Value("${jwt.expirationMs}") long expirationMs) {
        // Expecting a Base64-encoded secret that is at least 256 bits (32 bytes) for HMAC-SHA algorithms.
        if (secret == null || secret.isBlank()) {
            // In dev only: generate a secure random key and warn. Production should set a strong secret.
            log.warn("No jwt.secret provided — generating an ephemeral key for development. Set a secure base64-encoded secret for production.");
            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } else {
            byte[] keyBytes = null;
            try {
                // try decoding as Base64 first
                keyBytes = Decoders.BASE64.decode(secret);
            } catch (IllegalArgumentException e) {
                // not base64 — fall back to raw bytes
                keyBytes = secret.getBytes();
            }

            if (keyBytes.length < 32) {
                throw new IllegalArgumentException("The provided jwt.secret is too weak (" + (keyBytes.length * 8) + " bits). Provide a base64-encoded key with at least 256 bits (32 bytes). Example: `openssl rand -base64 32`.\n");
            }

            this.key = Keys.hmacShaKeyFor(keyBytes);
        }

        this.expirationMs = expirationMs;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
