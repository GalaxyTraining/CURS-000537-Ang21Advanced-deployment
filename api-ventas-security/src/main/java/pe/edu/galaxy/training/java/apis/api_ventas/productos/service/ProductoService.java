package pe.edu.galaxy.training.java.apis.api_ventas.productos.service;

import pe.edu.galaxy.training.java.apis.api_ventas.exceptions.ServiceException;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {
	
	List<ProductoResponseDto> findAll() throws ServiceException;
	
	List<ProductoResponseDto> findByNombre(String nombre) throws ServiceException;
	
	Long  save(ProductoRequestDto productoRequestDto) throws ServiceException;

	// TODO
	void delete (Long id) throws ServiceException;

}
