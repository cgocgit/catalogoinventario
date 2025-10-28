package mx.com.mesaregia.catalogoinventario.application.inventario;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.MovimientoInventario;
import mx.com.mesaregia.catalogoinventario.repository.MovimientoInventarioRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

	private final MovimientoInventarioRepository repository;
	/**
	 * 
	 */
	public MovimientoInventarioServiceImpl(MovimientoInventarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public void registraMovimiento(MovimientoInventario movimientoInventario) {
		repository.saveAndFlush(movimientoInventario);
	}

}
