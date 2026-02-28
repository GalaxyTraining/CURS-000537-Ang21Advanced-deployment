package pe.edu.galaxy.training.java.apis.api_ventas.categorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.mappers.CategoriaMapper;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.repository.CategoriaRepository;
import pe.edu.galaxy.training.java.apis.api_ventas.exceptions.ServiceException;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final CategoriaMapper categoriaMapper;
	
	public CategoriaServiceImpl(
			CategoriaRepository categoriaRepository,
			CategoriaMapper categoriaMapper) {
		this.categoriaRepository = categoriaRepository;
		this.categoriaMapper=categoriaMapper;
	}

	@Override
	public Optional<CategoriaResponseDto> findById(Long id) throws ServiceException {
		CategoriaEntity categoriaEntity=categoriaRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Categoria no existe"));
		return Optional.ofNullable(categoriaMapper.toDto(categoriaEntity));
	}

	@Override
	public List<CategoriaResponseDto> findAll() throws ServiceException {
		//return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).toList();
		return categoriaRepository.findAllCustom().stream().map(categoriaMapper::toDto).toList();
	}

	@Override
	public Long save(CategoriaRequestDto categoriaDto) throws ServiceException {
		CategoriaEntity categoriaEntity=  categoriaMapper.toEntity(categoriaDto);
		categoriaEntity.setEstado("1");
		CategoriaEntity retCategoriaEntity= categoriaRepository.save(categoriaEntity);
		return retCategoriaEntity.getId();
	}

	@Override
	public void delete(Long id) throws ServiceException {
		this.categoriaRepository.deleteLogic(id);		
	}

	@Override
	public List<CategoriaResponseDto> findByNombre(String nombre) throws ServiceException {
		nombre= "%"+((nombre==null)?"":nombre.trim())+"%";
		return categoriaRepository.findByNombre(nombre).stream().map(categoriaMapper::toDto).toList();
	}

}
