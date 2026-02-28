package pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CategoriaEntity")
@Table(name = "tbl_categorias")
public class CategoriaEntity {
	
	@Id
	@Column(name ="categoria_id" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="nombre_corto", unique = true, nullable = false,length = 60)
	private String nombreCorto;

	@Column(name ="nombre_largo", unique = true,  nullable = false,length = 120)
	private String nombreLargo;


	@Column(name ="estado" )
	private String estado;

	public CategoriaEntity(Long id) {
		this.id = id;
	}
	public CategoriaEntity(Long id, String nombreLargo) {
		//this.id = id;
		this(id);
		this.nombreLargo=nombreLargo;
	}

}

