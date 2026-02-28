package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // "ROLE_USER", "ROLE_ADMIN"

    public RoleEntity() {
    }
    public RoleEntity(String name) {
        this.name = name;
    }
}
