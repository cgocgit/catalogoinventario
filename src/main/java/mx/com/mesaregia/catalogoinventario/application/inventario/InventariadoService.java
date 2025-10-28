package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.domain.Inventario;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface InventariadoService {

	Inventario obtener(int idAlmacen, int idArticulo);
	
	void actualizar(Inventario inventario);

	/**
	 * @return
	 */
	Collection<Inventario> obtener();
	
	Inventario registrar(Inventario inventario);
}
