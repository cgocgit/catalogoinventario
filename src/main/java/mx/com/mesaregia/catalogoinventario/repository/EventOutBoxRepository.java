package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.EventOutbox;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface EventOutBoxRepository extends JpaRepository<EventOutbox, Integer> {

}
