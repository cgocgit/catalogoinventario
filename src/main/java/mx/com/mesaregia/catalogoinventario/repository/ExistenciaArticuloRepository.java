package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ExistenciaArticuloRepository extends JpaRepository<ExistenciaArticulo, Integer> {

	@Query(value = "SELECT COUNT(e) FROM ExistenciaArticulo e JOIN e.almacen al JOIN e.articulo ar WHERE al.idAlmacen = :idAlmacen AND ar.idArticulo = :idArticulo")
	Long countExistenciaArticuloInAlmacen(int idAlmacen, int idArticulo);
}
