package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.dto.AuthResponse;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.entity.RefreshTokenEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.entity.RoleEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.entity.UserEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.repository.RefreshTokenRepository;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.repository.UserRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static pe.edu.galaxy.training.java.apis.api_ventas.seguridad.constants.SeguridadConstants.REFRESH_TOKEN_EXPIRATION;

@Slf4j
@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshRepo;
    private final UserRepository userRepo;

    public AuthService(AuthenticationManager authManager, JwtService jwtService,
                       RefreshTokenRepository refreshRepo, UserRepository userRepo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.refreshRepo = refreshRepo;
        this.userRepo = userRepo;
    }

    public AuthResponse login(String username, String password) {
        log.info("username =>{}, password =>{}",username,password);
        //authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            log.info("authenticate OK");
        } catch (Exception e) {
            log.error("authenticate FAILED: {}", e.getClass().getName(), e);
            throw e;
        }
        UserEntity userEntity=  userRepo.findByUsername(username).orElseThrow();
        log.info("userEntity ={}",userEntity);
        CustomUserDetails userDetails = new CustomUserDetails(userEntity);
        String access = jwtService.generateAccessToken(userDetails);
        String refresh = createRefreshToken(username);

        return new AuthResponse(access, refresh);
    }

    // ROTACIÓN: invalida el refresh viejo y crea uno nuevo
    public AuthResponse refresh(String refreshToken) {
        RefreshTokenEntity stored = refreshRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token invalid"));

/*        if (stored.isRevoked() || stored.getExpiresAt().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired/revoked");
        }*/

        // Rotación
        stored.setRevoked(true);
        stored.setRevokedAt(Instant.now());
        refreshRepo.save(stored);

        UserEntity user = stored.getUser();

        UserEntity userEntity= new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPasswordHash(user.getPasswordHash());
        userEntity.setRoles(user.getRoles().stream().map(r -> new RoleEntity(r.getName())).toList());
        CustomUserDetails userDetails = new CustomUserDetails(userEntity);



        String newAccess = jwtService.generateAccessToken(userDetails);
        String newRefresh = createRefreshToken(user.getUsername());

        return new AuthResponse(newAccess, newRefresh);
    }

    public void logout(String refreshToken) {
        refreshRepo.findByToken(refreshToken).ifPresent(rt -> {
            rt.setRevoked(true);
            rt.setRevokedAt(Instant.now());
            refreshRepo.save(rt);
        });
    }

    private String createRefreshToken(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        String token = UUID.randomUUID().toString() + "." + UUID.randomUUID();

        RefreshTokenEntity rt = new RefreshTokenEntity();
        rt.setToken(token);
        rt.setUser(user);
        rt.setExpiresAt(Instant.now().plus(Duration.ofMinutes(REFRESH_TOKEN_EXPIRATION)));
        rt.setRevoked(false);

        refreshRepo.save(rt);
        return token;
    }
}

