package pe.edu.galaxy.training.java.apis.api_ventas.productos.mapper;

import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.entity.ProductoEntity;

public interface ProductoMapper {
	
	ProductoResponseDto toDto(ProductoEntity productoEntity);
	
	ProductoEntity toEntity(ProductoRequestDto productoRequestDto);

}
