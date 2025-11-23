package mx.com.mesaregia.catalogoinventario.application.catalogo;


import java.util.Date;

import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 * Constructor de instancias de Paquete.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface PaqueteBuilder {
	/**
	 * 
	 * @return
	 */
	Paquete get();
	/**
	 * 
	 */
	void preparar();
	/**
	 * 
	 * @param codiPaquete
	 */
	void setCodigoPaquete(String codiPaquete);
	/**
	 * 
	 * @param descripcion
	 */
	void setDescripcion(String descripcion);
	/**
	 * 
	 * @param nombrePaquete
	 */
	void setNombrePaquete(String nombrePaquete);
	/**
	 * 
	 * @param precio
	 */
	void setPrecio(Double precio);
	/**
	 * 
	 * @param usuarioCreacion
	 */
	void setUsuarioCreacion(String usuarioCreacion);
	/**
	 * 
	 * @param idPaquete
	 */
	void setIdPaquete(Integer idPaquete);
	/**
	 * 
	 * @param fechaRegistro
	 */
	void setFechaRegistro(Date fechaRegistro);
}
