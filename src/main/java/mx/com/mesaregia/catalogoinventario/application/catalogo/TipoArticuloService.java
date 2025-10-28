package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
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
