package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.domain.Inventario;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface InventarioService {

	ExistenciaArticulo agregarArticulo(int idInventario, int idArticulo, String codigoUnidad) throws NotFoundException, ItsExistException;
	
	void actualizarEstadoArticulo(int idExistenciaArticulo, EstadoArticulo estado) throws NotFoundException;
	
	Collection<Inventario> consultarInventario();
}
