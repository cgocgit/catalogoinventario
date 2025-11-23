package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
 * Transaccional para el manejo del <a href="mx.com.mesaregia.catalogoinventario.application.catalogo.TipoArticulo">TipoArticulo</a>.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface TipoArticuloService {

	TipoArticulo obtenerTipoArticulo(int idTipoArticulo);
	
	Collection<TipoArticulo> obtenerTipoArticulos();
}
