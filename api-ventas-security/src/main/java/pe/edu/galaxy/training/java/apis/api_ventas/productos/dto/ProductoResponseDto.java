package pe.edu.galaxy.training.java.apis.api_ventas.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoResponseDto {
	
	private Long id;
	
	private String nombre;

	private String descripcion;

	private Double precio;

	private Integer cantidad;

	private CategoriaResponseDto categoria;

}
