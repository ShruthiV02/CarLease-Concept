package com.carlease.service.carleaseservice.authservice;

import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.carlease.service.carleaseservice.model.UserPrincipal;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Token provider Class
 * for generating Token and Validating
 * @author Shruthi
 *
 */
@Component
public class JWTTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    /*
     * Method to generate token
     */
    public String generateToken(UserPrincipal userPrincipal){

        List<String> roles = userPrincipal
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts
                .builder()
                .setIssuer("Demo App")
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs * 10000))
                .claim("Roles", roles)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /*
     * Validating the token
     */
    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException ex) {
        	logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
        	logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	logger.error("JWT claims string is empty.");
        }
        return false;


    }
    public String getUserNameFromToken(String token){
        return  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }


}
