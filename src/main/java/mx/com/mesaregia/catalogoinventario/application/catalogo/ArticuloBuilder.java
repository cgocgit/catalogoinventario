package mx.com.mesaregia.catalogoinventario.application.catalogo;

import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ArticuloBuilder {

	void preparar();
	
	void setCategoria(Categoria categoria);
	
	void setColor(Color color);
	
	void setDescripcion(String descripcion);
	
	void setFechaRegistro();
	
	void setNombre(String nombre);
	
	void setTipo(TipoArticulo tipoArticulo);
	
	void setUnidadMedida(String unidadMedida);
	
	void setUsuario(String usuario);
	
	Articulo get();

	/**
	 * @param idArticulo
	 */
	void setIdArticulo(Long idArticulo);
}
