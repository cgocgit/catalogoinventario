package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface DetallePaqueteService<T, V> {

	T agregarAPaquete(Paquete paquete, V v, int cantidad, double precio) throws NotFoundException, ItsExistException;
	
	T agregarAPaquete(int idPaquete, int v, int cantidad, double precio) throws NotFoundException, ItsExistException;;
	
	Collection<T> consultarDetalleEnPaquete(int idPaquete);
	
	void quitarDelPaquete(int idDetallePaquete) throws NotFoundException;
}
