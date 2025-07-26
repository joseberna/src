package com.retosbk.comerciantesapi.service;

import com.retosbk.comerciantesapi.entity.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final long EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000; // 24 horas en milisegundos

    /**
     * Genera un token JWT con el correo del usuario como sujeto.
     */
    public String generateToken(String correoElectronico) {
        return Jwts.builder()
                .setSubject(correoElectronico)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el correo (username) desde un token JWT.
     */
    public String extractUsername(String token) {
        String email = extractClaim(token, Claims::getSubject);
        System.out.println("extractUsername email: " + email);
        return  email;
    }

    /**
     * Verifica si un token es válido para un usuario específico.
     */
    public boolean isTokenValid(String token, Usuario usuario) {
        final String correo = extractUsername(token);
        return correo.equals(usuario.getCorreoElectronico()) && !isTokenExpired(token);
    }

    /**
     * Extrae cualquier dato desde los claims del token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = getClaims(token);
        return resolver.apply(claims);
    }

    /**
     * Extrae todos los claims desde un token.
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token ya expiró.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * Obtiene la clave para firmar/verificar el JWT.
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }



}
