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
public class ExistenciaArticuloBuilderImpl implements ExistenciaArticuloBuilder {

	private ExistenciaArticulo bean;
	
	/**
	 * 
	 */
	public ExistenciaArticuloBuilderImpl() {
		/* Constructor principal */
	}

	@Override
	public void preparar() {
		this.bean = new ExistenciaArticulo();
	}

	@Override
	public void setCodigoUnidad(String codigoUnidad) {
		this.bean.setCodigoUnidad(codigoUnidad);
	}

	@Override
	public void setEstadoArticulo(EstadoArticulo estadoArticulo) {
		this.bean.setEstado(estadoArticulo);
	}

	@Override
	public void setAlmacen(Almacen almacen) {
		this.bean.setAlmacen(almacen);
	}

	@Override
	public void setArticulo(Articulo articulo) {
		this.bean.setArticulo(articulo);
	}

	@Override
	public ExistenciaArticulo get() {
		return this.bean;
	}

}
