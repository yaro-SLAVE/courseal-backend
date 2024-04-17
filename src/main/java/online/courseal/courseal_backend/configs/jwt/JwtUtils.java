package online.courseal.courseal_backend.configs.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${JWT_BASE64_SECRET_KEY}")
    private String jwtSecret;
    @Value("${JWT_EXPIRATION_MS}")
    private Long jwtExpirationMs;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8)));

        return Jwts.builder()
                .setSubject(userPrincipal.getUserTag())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public String generateTokenFromUserTag(String userTag) {

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8)));
        return Jwts.builder()
                .setSubject(userTag)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date())
                .getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException();
        }
    }

    public String getUserTagFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

}
