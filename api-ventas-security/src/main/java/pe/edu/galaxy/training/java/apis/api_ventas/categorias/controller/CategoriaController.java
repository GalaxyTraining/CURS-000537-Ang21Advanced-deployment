package pe.edu.galaxy.training.java.apis.api_ventas.categorias.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.dto.CategoriaResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.service.CategoriaService;
import pe.edu.galaxy.training.java.apis.api_ventas.exceptions.CustomBadRequest;

@Slf4j
@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
	
	private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDto> findById(
			@PathVariable("id") Long id){
        return categoriaService.findById(id)
				.map(categoriaResponseDto -> ResponseEntity.ok()
						.body(categoriaResponseDto))
				.orElseGet(() -> ResponseEntity.noContent().build());

	}
    @GetMapping
	public ResponseEntity<List<CategoriaResponseDto>> findAll(){
		
		List<CategoriaResponseDto> categoriaResponseDtos = categoriaService.findAll();
		
		if (categoriaResponseDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(categoriaResponseDtos);
		}
		
	}
	
	@GetMapping("/by-nombre")
	public ResponseEntity<List<CategoriaResponseDto>> findByNombre(@RequestParam("nombre") String nombre){
		
		List<CategoriaResponseDto> categoriaResponseDtos = categoriaService.findByNombre(nombre);
		
		if (categoriaResponseDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(categoriaResponseDtos);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CategoriaRequestDto categoriaRequestDto,
								  BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			throw new CustomBadRequest(bindingResult);
		}
		Long id = categoriaService.save(categoriaRequestDto);
		
		if (id==null) {
			return ResponseEntity.badRequest().build();
		} else {
			return new ResponseEntity<>(Map.of("id",id),HttpStatus.CREATED);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		categoriaService.delete(id);
		return ResponseEntity.ok().build();
		
	}

}
