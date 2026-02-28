package pe.edu.galaxy.training.java.apis.api_ventas.usuarios.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.apis.api_ventas.categorias.entity.CategoriaEntity;
import pe.edu.galaxy.training.java.apis.api_ventas.usuarios.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
	//JPQL
	@Query(nativeQuery = false,
			value = "select u from UsuarioEntity u where u.usuario=:usuario and u.clave=:clave and u.estado='1'")
	Optional<UsuarioEntity> authorization(@Param("usuario") String usuario, @Param("clave") String clave);

}
