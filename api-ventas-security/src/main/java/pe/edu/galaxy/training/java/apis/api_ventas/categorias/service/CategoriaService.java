package pe.edu.galaxy.training.java.apis.api_ventas.categorias.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.exceptions.ServiceException;

public interface CategoriaService {

	Optional<CategoriaResponseDto> findById(Long id) throws ServiceException;

	List<CategoriaResponseDto> findAll() throws ServiceException;
	
	List<CategoriaResponseDto> findByNombre(String nombre) throws ServiceException;
	
	Long  save(CategoriaRequestDto categoriaDto) throws ServiceException;

	// TODO
	void delete (Long id) throws ServiceException;
	

}
