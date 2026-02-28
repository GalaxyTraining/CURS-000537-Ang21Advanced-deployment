package pe.edu.galaxy.training.java.apis.api_ventas.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "UsuarioEntity")
@Table(name = "tbl_usuario")
public class UsuarioEntity {

	@Id
	@Column(name ="usuario_id" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="nombre_completo", unique = true, nullable = false,length = 60)
	private String nombreCompleto;

	@Column(name ="usuario",  nullable = false,length = 120)
	private String usuario;

	@Column(name ="clave", nullable = false,length = 120)
	private String clave;

	@Column(name ="estado" )
	private String estado;

	public UsuarioEntity(Long id) {
		this.id = id;
	}
}

