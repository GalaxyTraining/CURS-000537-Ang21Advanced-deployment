package pe.edu.galaxy.training.java.apis.api_ventas.categorias.mappers;

import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;

public interface CategoriaMapper {
	
	CategoriaResponseDto toDto(CategoriaEntity categoriaEntity);
	
	CategoriaEntity toEntity(CategoriaRequestDto categoriaRequestDto);

}
