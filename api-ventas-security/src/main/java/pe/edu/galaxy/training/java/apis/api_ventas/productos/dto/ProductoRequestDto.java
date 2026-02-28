package pe.edu.galaxy.training.java.apis.api_ventas.productos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoRequestDto {

	@NotNull(message = "El nombre es requerido")
	@Size(min = 2,max = 10, message = "El nombre es requerido y debe tener como mínimo {min} y {max} carateres")
	private String nombre;

	@NotNull(message = "La descripcion es requerido")
	@Size(min = 5,max = 100, message = "La descripcion es requerido y debe tener como mínimo {min} y {max} carateres")
	private String descripcion;

	@NotNull(message = "El precio  es requerido")
	@Positive(message = "El precio debe ser mayor que cero")
	private Double precio;

	@NotNull(message = "La cantidad es requerida")
	@PositiveOrZero(message = "La cantidad debe ser mayor o igual que cero")
	private Integer cantidad;

	@NotNull(message = "La caategoria es requerida")
	@Positive(message = "La caategoria debe ser mayor que cero")
	private Long categoriaId;

}
