package mx.com.mesaregia.catalogoinventario.application.inventario;

import mx.com.mesaregia.catalogoinventario.domain.MovimientoInventario;

/**
 * Transaccional de Movimientos en Inventario.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface MovimientoInventarioService {

	void registraMovimiento(MovimientoInventario movimientoInventario);
}
