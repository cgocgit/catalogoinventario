package mx.com.mesaregia.catalogoinventario.application.catalogo;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;

/**
 * Constructor de entidad Servicio.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ServicioBuilder {
	/**
	 * Prepara una instancia de la entidad Servicio.
	 */
	void preparar();
	/**
	 * 
	 * @param categoria
	 */
	void setCategoria(Categoria categoria);
	/**
	 * 
	 * @param codigoServicio
	 */
	void setCodigoServicio(String codigoServicio);
	/**
	 * 
	 * @param nombreServicio
	 */
	void setNombreServicio(String nombreServicio);
	/**
	 * 
	 * @param descripcion
	 */
	void setDescripcion(String descripcion);
	/**
	 * 
	 * @param tipoServicio
	 */
	void setTipoServicio(TipoServicio tipoServicio);
	/**
	 * 
	 * @param costo
	 */
	void setCosto(Double costo);
	/**
	 * 
	 * @param tarifaBase
	 */
	void setTarifaBase(Double tarifaBase);
	/**
	 * 
	 * @param idServicio
	 */
	void setIdServicio(Integer idServicio);
	/**
	 * 
	 * @return
	 */
	Servicio get();
}
