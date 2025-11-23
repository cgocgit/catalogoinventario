package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.dto.ExistenciaArticuloDTO;
import mx.com.mesaregia.catalogoinventario.dto.InventarioDTO;
import mx.com.mesaregia.catalogoinventario.dto.RegistroInventarioDTO;

/**
 * Serivicio para logica de negocio en inventarios.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface InventarioBussines {

	ExistenciaArticuloDTO registrarInventario(RegistroInventarioDTO registroInventario);
	
	Collection<InventarioDTO> consultarInventario(int idAlmacen);
}
