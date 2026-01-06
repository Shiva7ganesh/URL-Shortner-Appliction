package com.shivaganesh.Url_shortner_Application.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final  String SECRET ="e2a9e96efd785fcf3bfdd979752577b6bb6f1f0269f6098acdc63304366911e5";
//    private static final long EXPIRY = 1000 * 60 * 60 * 24 *7;

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String uid){
        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String extractUid(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
