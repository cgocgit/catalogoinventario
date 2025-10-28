package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface CategoriaService {

	Categoria obtenerCategoria(int idCategoria);
	
	Collection<Categoria> obtenerCategorias();
}
