package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ArticuloService {

		
	Collection<Articulo> obtenerArticulos();
	
	Articulo obtenerArticulo(Integer idArticulo) throws NotFoundException;
	
	void actualizarArticulo(Articulo articulo) throws NotFoundException;
	
	void bajarArticulo(Integer idArticulo) throws NotFoundException;
	
	Articulo registrarArticulo(Articulo articulo);
	
	
}
