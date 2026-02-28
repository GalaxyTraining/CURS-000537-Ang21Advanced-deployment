package pe.edu.galaxy.training.java.apis.api_ventas.productos.service;

import org.springframework.stereotype.Service;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.exceptions.ServiceException;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.entity.ProductoEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.mapper.ProductoMapper;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoResponseDto> findAll() throws ServiceException {
        return productoRepository.findAllCustom().stream().map(productoMapper::toDto).toList();
    }

    @Override
    public List<ProductoResponseDto> findByNombre(String nombre) throws ServiceException {
        nombre= "%"+((nombre==null)?"":nombre.trim())+"%";
        return productoRepository.findByNombre(nombre).stream().map(productoMapper::toDto).toList();
    }

    @Override
    public Long save(ProductoRequestDto productoRequestDto) throws ServiceException {
        ProductoEntity productoEntity=  productoMapper.toEntity(productoRequestDto);
        productoEntity.setEstado("1");
        ProductoEntity retProductoEntity= productoRepository.save(productoEntity);
        return retProductoEntity.getId();
    }

    @Override
    public void delete(Long id) throws ServiceException {
        this.productoRepository.deleteLogic(id);
    }
}
