package com.mycompany.njtprojekat.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Koristite jak ključ za produkciju! (Minimalno 256 bita)
    // Za demo, možemo generisati ključ (treba da bude Base64)
    // NAPOMENA: Za produkciju, ključ treba biti učitan iz env. varijable.
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 1 sat isteka tokena
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 1000; 

    // Korigovano ime: extractUsername
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Korigovano ime: extractExpiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Preimenovano: getClaimFromToken -> extractClaim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        // Koristi se korigovana metoda
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
