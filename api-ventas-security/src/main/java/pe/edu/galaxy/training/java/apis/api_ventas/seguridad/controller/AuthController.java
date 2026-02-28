package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.dto.AuthResponse;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.dto.LoginRequest;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.dto.RefreshRequest;
import pe.edu.galaxy.training.java.apis.api_ventas.seguridad.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        return authService.login(req.username(), req.password());
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest req) {
        return authService.refresh(req.refreshToken());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshRequest req) {
        authService.logout(req.refreshToken());
    }
}