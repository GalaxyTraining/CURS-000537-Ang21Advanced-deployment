package pe.edu.galaxy.training.java.apis.api_ventas.categorias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
	
	//JPQL
	@Query(nativeQuery = false, value = "select c from CategoriaEntity c")//where c.estado='1'
	List<CategoriaEntity> findAllCustom();
	
	
	//JPQL
		@Query(nativeQuery = false, 
				value = "select c from CategoriaEntity c "
						+ " where (c.nombreCorto like :nombre "
						+ " or  c.nombreLargo like :nombre) "
						+ " and c.estado='1'")
		List<CategoriaEntity> findByNombre(@Param("nombre") String nombre);
		

	// SQL
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update tbl_categorias set estado='0' where categoria_id=:id")
	void deleteLogic(@Param("id") Long id);
	
}
