package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 512)
    private String token; // (mejor si lo guardas hasheado, pero para empezar puede ir plano)

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean revoked = false;

    private Instant createdAt = Instant.now();
    private Instant revokedAt;
}
