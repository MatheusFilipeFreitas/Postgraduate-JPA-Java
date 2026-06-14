package com.mathffreitas.jpaunipds.security.utils.token;

import com.mathffreitas.jpaunipds.exceptions.InvalidHeaderException;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class TokenUtils {

    private static final String EMITTED_BY = "a5e3f2b1d0c9b8a7f6e5d4c3b2a1f0e9";
    private static final long EXPIRATION = 60 * 60 * 1000;
    private static final String SECRET_KEY = "b9a3d9222c837517efd019f2a4de2fdb2810f639";

    private TokenUtils() {
    }

    public static BearerToken enconde(User user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            String jwtToken = Jwts.builder()
                    .subject(user.getId().toString())
                    .claims(buildUserClaims(user))
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .issuer(EMITTED_BY)
                    .signWith(key)
                    .compact();

            return new BearerToken(jwtToken);
        } catch (Exception ex) {
            throw new InvalidHeaderException("Unable to generate token");
        }
    }

    private static Map<String, String> buildUserClaims(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        return claims;
    }

    public static Authentication decode(HttpServletRequest request) {
        String token = extractToken(request.getHeader("Authorization"));
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        try {
            Claims claims = Jwts.parser()
                    .verifyWith((javax.crypto.SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            validateClaims(claims);

            return new UsernamePasswordAuthenticationToken(
                    claims.getSubject(),
                    null,
                    Collections.emptyList());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidHeaderException("Invalid or expired token");
        }
    }

    static String extractToken(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new InvalidHeaderException("Authorization header missing");
        }

        String value = authorizationHeader.trim();
        if (value.regionMatches(true, 0, "Bearer ", 0, 7)) {
            value = value.substring(7).trim();
        }

        if (value.isBlank()) {
            throw new InvalidHeaderException("Authorization token missing");
        }

        return value;
    }

    private static void validateClaims(Claims claims) {
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        Date expiration = claims.getExpiration();

        if (subject == null || subject.isBlank()) {
            throw new InvalidHeaderException("Invalid token subject");
        }
        if (!EMITTED_BY.equals(issuer)) {
            throw new InvalidHeaderException("Invalid token issuer");
        }
        if (expiration == null || expiration.before(new Date())) {
            throw new InvalidHeaderException("Token expired");
        }
    }
}
