package mx.com.mesaregia.catalogoinventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface DetallePaqueteArticuloRepository extends JpaRepository<DetallePaqueteArticulo, Integer> {

	@Query(value =  "SELECT d FROM DetallePaqueteArticulo d JOIN d.paquete p WHERE p.idPaquete = :idPaquete")
	List<DetallePaqueteArticulo> findByIdPaquete(Integer idPaquete);
}
