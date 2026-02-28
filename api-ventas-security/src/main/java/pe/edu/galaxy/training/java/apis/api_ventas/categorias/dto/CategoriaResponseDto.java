package pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaResponseDto {
	
	private Long id;
	
	private String nombreCorto;

	private String nombreLargo;

	private String estado;
	
}
