package com.manu.alkemy.disney.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerador {


    public String generarToken(Authentication authentication) {

        String username = authentication.getName();
        Date tiempoActual = new Date();
        Date expiracionToken = new Date(tiempoActual.getTime() + 3000000);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiracionToken)
                .signWith(SignatureAlgorithm.HS512, "firma")
                .compact();
        return token;
    }

    public String obtenerUsernameDeJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("firma")
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey("firma").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
            return false;
        }
    }
}
