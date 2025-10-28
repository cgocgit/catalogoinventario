package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.mesaregia.catalogoinventario.domain.Inventario;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

	@Query(value = "SELECT i FROM Inventario i JOIN i.articulo a JOIN i.almacen al WHERE a.idArticulo = :idArticulo AND al.idAlmacen = :idAlmacen")
	Inventario findByIdArticuloAndIdAlmacen(int idArticulo, int idAlmacen);
}
