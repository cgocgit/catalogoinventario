package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ServicioService {

	
	void bajarServicio(Integer idServicio) throws NotFoundException;
	
	Collection<Servicio> obtenerServicios();
	
	Servicio obtenerServicio(Integer idServicio) throws NotFoundException;
	
	void actualizarServicio(Servicio servicio) throws NotFoundException;
		
	Servicio registrarServicio(Servicio servicio);
	
}
