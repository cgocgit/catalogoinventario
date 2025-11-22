package mx.com.mesaregia.catalogoinventario.application.catalogo;

import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
 * Constructor de la entidad Articulo.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ArticuloBuilder {
	/**
	 * Preparaci&oacute; de la instancia.
	 */
	void preparar();
	/**
	 * 
	 * @param categoria
	 */
	void setCategoria(Categoria categoria);
	/**
	 * 
	 * @param color
	 */
	void setColor(Color color);
	/**
	 * 
	 * @param descripcion
	 */
	void setDescripcion(String descripcion);
	/**
	 * 
	 */
	void setFechaRegistro();
	/**
	 * 
	 * @param nombre
	 */
	void setNombre(String nombre);
	/**
	 * 
	 * @param tipoArticulo
	 */
	void setTipo(TipoArticulo tipoArticulo);
	/**
	 * 
	 * @param unidadMedida
	 */
	void setUnidadMedida(String unidadMedida);
	/**
	 * 
	 * @param usuario
	 */
	void setUsuario(String usuario);
	/**
	 * 
	 * @return
	 */
	Articulo get();
	/**
	 * @param idArticulo
	 */
	void setIdArticulo(Long idArticulo);
}
