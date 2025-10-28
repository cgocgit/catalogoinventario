package mx.com.mesaregia.catalogoinventario.application.catalogo;

import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class ServicioBuilderImpl implements ServicioBuilder {

	private Servicio servicio;
	
	/**
	 * 
	 */
	public ServicioBuilderImpl() {
		/* Constructor principal */
	}

	@Override
	public void setCategoria(Categoria categoria) {
		servicio.setCategoria(categoria);
	}


	@Override
	public Servicio get() {
		return servicio;
	}

	@Override
	public void preparar() {
		servicio = new Servicio();
	}

	@Override
	public void setCodigoServicio(String codigoServicio) {
		servicio.setCodigoServicio(codigoServicio);
	}

	@Override
	public void setNombreServicio(String nombreServicio) {
		servicio.setNombreServicio(nombreServicio);
	}

	@Override
	public void setDescripcion(String descripcion) {
		servicio.setDescripcion(descripcion);
	}

	@Override
	public void setTipoServicio(TipoServicio tipoServicio) {
		servicio.setTipoServicio(tipoServicio);
	}

	@Override
	public void setCosto(Double costo) {
		servicio.setCosto(costo);
	}

	@Override
	public void setTarifaBase(Double tarifaBase) {
		servicio.setTarifaBase(tarifaBase);
	}

	@Override
	public void setIdServicio(Integer idServicio) {
		servicio.setIdServicio(idServicio);
	}


}
