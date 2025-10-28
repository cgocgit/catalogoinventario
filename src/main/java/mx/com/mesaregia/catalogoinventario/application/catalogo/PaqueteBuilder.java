package mx.com.mesaregia.catalogoinventario.application.catalogo;


import java.util.Date;

import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface PaqueteBuilder {

	Paquete get();
	
	void preparar();
	
	void setCodigoPaquete(String codiPaquete);
	
	void setDescripcion(String descripcion);
	
	void setNombrePaquete(String nombrePaquete);
	
	void setPrecio(Double precio);
	
	void setUsuarioCreacion(String usuarioCreacion);
	
	void setIdPaquete(Integer idPaquete);
	
	void setFechaRegistro(Date fechaRegistro);
}
