package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.CorteInventario;

/**
 * CRUD para CorteInventario.
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface CorteInventarioRepository extends JpaRepository<CorteInventario, Integer> {

}
