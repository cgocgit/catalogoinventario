package mx.com.mesaregia.catalogoinventario.application.catalogo;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public abstract class DetallePaqueteAbstract<V> {

	protected abstract V buscarElemento(int id);
	
	protected abstract Paquete buscarPaquete(int id);
	
	protected abstract void validaExistencia(Paquete paquete, V v) throws ItsExistException;

}
