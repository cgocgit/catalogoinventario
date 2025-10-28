package mx.com.mesaregia.catalogoinventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface DetallePaqueteServicioRepository extends JpaRepository<DetallePaqueteServicio, Integer> {

	@Query(value =  "SELECT d FROM DetallePaqueteServicio d JOIN d.paquete p WHERE p.idPaquete = :idPaquete")
	List<DetallePaqueteServicio> findByIdPaquete(Integer idPaquete);
}
