package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.util.List;

import static pe.edu.galaxy.training.java.apis.api_ventas.seguridad.constants.SeguridadConstants.TOKEN_EXPIRATION;

@Service
public class JwtService {

    private final Key key = Keys.hmacShaKeyFor("super_secret_key_super_secret_key_123456".getBytes());

    public String generateAccessToken(CustomUserDetails user) {
        //CustomUserDetails user = (CustomUserDetails) userDetails;
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("fullName", user.getFullName())
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String jwt) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody().getSubject();
    }

    public List<String> extractRoles(String jwt) {
        Object roles = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody().get("roles");
        if (roles instanceof List<?> list) return list.stream().map(String::valueOf).toList();
        return List.of();
    }
}
