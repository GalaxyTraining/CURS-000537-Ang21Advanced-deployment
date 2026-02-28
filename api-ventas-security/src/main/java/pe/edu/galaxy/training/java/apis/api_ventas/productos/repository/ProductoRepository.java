package pe.edu.galaxy.training.java.apis.api_ventas.productos.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.productos.entity.ProductoEntity;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	
	//JPQL
	@Query(nativeQuery = false, value = "select c from ProductoEntity c where c.estado='1'")
	List<ProductoEntity> findAllCustom();

	//JPQL
	@Query(nativeQuery = false,
				value = "select c from ProductoEntity c "
						+ " where c.nombre like :nombre "
						+ " and c.estado='1'")
	List<ProductoEntity> findByNombre(@Param("nombre") String nombre);
	// SQL
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update tbl_productos set estado='0' where producto_id=:id")
	void deleteLogic(@Param("id") Long id);
	
}
