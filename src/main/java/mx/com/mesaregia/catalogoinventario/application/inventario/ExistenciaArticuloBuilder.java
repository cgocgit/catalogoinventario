package mx.com.mesaregia.catalogoinventario.application.inventario;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ExistenciaArticuloBuilder {

	void preparar();
	
	void setCodigoUnidad(String codigoUnidad);
	
	void setEstadoArticulo(EstadoArticulo estadoArticulo);
	
	void setAlmacen(Almacen almacen);
	
	void setArticulo(Articulo articulo);
	
	ExistenciaArticulo get();
	
}
