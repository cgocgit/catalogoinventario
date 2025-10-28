package mx.com.mesaregia.catalogoinventario.application.catalogo;

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
public interface ServicioBuilder {

	void preparar();
	
	void setCategoria(Categoria categoria);
	
	void setCodigoServicio(String codigoServicio);
	
	void setNombreServicio(String nombreServicio);
	
	void setDescripcion(String descripcion);
	
	void setTipoServicio(TipoServicio tipoServicio);
	
	void setCosto(Double costo);
	
	void setTarifaBase(Double tarifaBase);
	
	void setIdServicio(Integer idServicio);
	
	Servicio get();
}
