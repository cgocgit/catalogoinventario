package mx.com.mesaregia.catalogoinventario.application.inventario;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;

/**
 * Constructor de entidades ExistenciaArticulo.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ExistenciaArticuloBuilder {

	/**
	 * Prepara la instancia de ExistenciaArticulo.
	 */
	void preparar();
	/**
	 * 
	 * @param codigoUnidad
	 */
	void setCodigoUnidad(String codigoUnidad);
	/**
	 * 
	 * @param estadoArticulo
	 */
	void setEstadoArticulo(EstadoArticulo estadoArticulo);
	/**
	 * 
	 * @param almacen
	 */
	void setAlmacen(Almacen almacen);
	/**
	 * 
	 * @param articulo
	 */
	void setArticulo(Articulo articulo);
	/**
	 * 
	 * @return
	 */
	ExistenciaArticulo get();
}
