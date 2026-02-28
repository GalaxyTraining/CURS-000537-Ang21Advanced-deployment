package pe.edu.galaxy.training.java.apis.api_ventas.categorias.mappers;

import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;

@Component
public class CategoriaMapperImpl implements CategoriaMapper {

	@Override
	public CategoriaResponseDto toDto(CategoriaEntity categoriaEntity) {
		
		return CategoriaResponseDto
				.builder()
				.id(categoriaEntity.getId())
				.nombreCorto(categoriaEntity.getNombreCorto())
				.nombreLargo(categoriaEntity.getNombreLargo())
				.estado(categoriaEntity.getEstado())
				.build();
	}

	@Override
	public CategoriaEntity toEntity(CategoriaRequestDto categoriaRequestDto) {
		
		return CategoriaEntity
				.builder()
				.nombreCorto(categoriaRequestDto.getNombreCorto())
				.nombreLargo(categoriaRequestDto.getNombreLargo())
				.build();
	}

}
