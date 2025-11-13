package mx.com.mesaregia.catalogoinventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.Servicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

	Optional<Servicio> findByIdServicioAndActivoTrue(Integer idServicio);
	
	List<Servicio> findByActivoTrue();
}
