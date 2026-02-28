package pe.edu.galaxy.training.java.apis.api_ventas.productos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ProductoEntity")
@Table(name = "tbl_productos")
public class ProductoEntity {
	
	@Id
	@Column(name ="producto_id" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="nombre", unique = true, nullable = false,length = 60)
	private String nombre;

	@Column(name ="descripcion", unique = true,  nullable = false,length = 120)
	private String descripcion;

	@Column(name ="precio", nullable = false)
	private Double precio;

	@Column(name ="cantidad", nullable = false)
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	private CategoriaEntity categoria;

	@Column(name ="estado" )
	private String estado;
	
}

