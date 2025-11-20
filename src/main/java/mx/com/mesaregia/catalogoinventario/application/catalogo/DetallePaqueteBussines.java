package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.dto.DetalleEnPaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.TipoDetalle;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface DetallePaqueteBussines {

	DetalleEnPaqueteDTO agregarAPaquete(int idPaquete, int v, int cantidad, double precio, TipoDetalle tipoDetalle) throws NotFoundException, ItsExistException;
	
	Collection<DetalleEnPaqueteDTO> consultarDetalleEnPaquete(int idPaquete);
	
	Paquete quitarDelPaquete(int idDetallePaquete, TipoDetalle tipoDetalle) throws NotFoundException;
}
