package pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDto {

	@NotNull(message = "El nombre corto es requerido")
	@Size(min = 2,max = 20, message = "El nombre corto es requerido y debe tener como mínimo {min} y {max} carateres")
	private String nombreCorto;

	@NotNull(message = "El nombre largo es requerido")
	@Size(min = 5,max = 120, message = "El nombre largo es requerido y debe tener como mínimo {min} y {max} carateres")
	private String nombreLargo;
	
}
