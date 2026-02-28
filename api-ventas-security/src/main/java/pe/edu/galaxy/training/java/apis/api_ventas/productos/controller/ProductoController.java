package pe.edu.galaxy.training.java.apis.api_ventas.productos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoRequestDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.dto.ProductoResponseDto;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.service.ProductoService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
	
	private final ProductoService productoService;
	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
    }
	
	@GetMapping
	public ResponseEntity<List<ProductoResponseDto>> findAll(){
		
		List<ProductoResponseDto> productoResponseDtos = productoService.findAll();
		
		if (productoResponseDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(productoResponseDtos);
		}
		
	}
	
	@GetMapping("/by-nombre")
	public ResponseEntity<List<ProductoResponseDto>> findByNombre(@RequestParam("nombre") String nombre){
		
		List<ProductoResponseDto> productoResponseDtos = productoService.findByNombre(nombre);
		
		if (productoResponseDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(productoResponseDtos);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ProductoRequestDto productoRequestDto,
								  BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			log.info("productoRequestDto {}",productoRequestDto);
			//throw new CustomBadRequest(bindingResult);
		}
		Long id = productoService.save(productoRequestDto);
		
		if (id==null) {
			return ResponseEntity.badRequest().build();
		} else {
			return new ResponseEntity<>(Map.of("id",id),HttpStatus.CREATED);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
			this.productoService.delete(id);
			return ResponseEntity.ok().build();		
		
	}

}
