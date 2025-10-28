package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface PaqueteService {


	void actualizarPaquete(Paquete paquete) throws NotFoundException;
	
	void bajarPaquete(Integer idPaquete) throws NotFoundException;
	
	Paquete obtenerPaquete(Integer idPaquete) throws NotFoundException;
	
	Collection<Paquete> obtenerPaquetes();
	
	Paquete registrarPaquete(Paquete paquete);
	
}
