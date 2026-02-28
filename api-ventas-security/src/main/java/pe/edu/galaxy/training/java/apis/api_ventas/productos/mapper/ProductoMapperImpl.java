package pe.edu.galaxy.training.java.apis.api_ventas.productos.mapper;

import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.mappers.CategoriaMapper;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.entity.ProductoEntity;

@Component
public class ProductoMapperImpl implements  ProductoMapper{

    private final CategoriaMapper categoriaMapper;

    public ProductoMapperImpl(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public ProductoResponseDto toDto(ProductoEntity productoEntity) {
        return ProductoResponseDto
                .builder()
                .id(productoEntity.getId())
                .nombre(productoEntity.getNombre())
                .descripcion(productoEntity.getDescripcion())
                .precio(productoEntity.getPrecio())
                .cantidad(productoEntity.getCantidad())
                .categoria(categoriaMapper.toDto(productoEntity.getCategoria()))
                .build();
    }

    @Override
    public ProductoEntity toEntity(ProductoRequestDto productoRequestDto) {
        return ProductoEntity
                .builder()
                .nombre(productoRequestDto.getNombre())
                .descripcion(productoRequestDto.getDescripcion())
                .precio(productoRequestDto.getPrecio())
                .cantidad(productoRequestDto.getCantidad())
                .categoria(new CategoriaEntity(productoRequestDto.getCategoriaId()))
                .build();
    }
}
