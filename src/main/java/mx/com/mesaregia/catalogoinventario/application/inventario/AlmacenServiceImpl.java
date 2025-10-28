package mx.com.mesaregia.catalogoinventario.application.inventario;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.repository.AlmacenRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class AlmacenServiceImpl implements AlmacenService {

	private final AlmacenRepository repository;
	
	/**
	 * 
	 */
	public AlmacenServiceImpl(AlmacenRepository repository) {
		this.repository = repository;
	}

	@Override
	public Almacen obtenerAlmacen(int idAlmacen) {
		return repository.findById(idAlmacen).orElse(null);
	}

}
