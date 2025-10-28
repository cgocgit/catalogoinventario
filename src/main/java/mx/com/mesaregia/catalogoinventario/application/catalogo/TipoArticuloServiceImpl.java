package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;
import mx.com.mesaregia.catalogoinventario.repository.TipoArticuloRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class TipoArticuloServiceImpl implements TipoArticuloService {

	private final TipoArticuloRepository tipoArticuloRepository;
	/**
	 * 
	 */
	public TipoArticuloServiceImpl(TipoArticuloRepository tipoArticuloRepository) {
		this.tipoArticuloRepository = tipoArticuloRepository;
	}

	@Override
	public TipoArticulo obtenerTipoArticulo(int idTipoArticulo) {
		return tipoArticuloRepository.findById(idTipoArticulo).get();
	}

	@Override
	public Collection<TipoArticulo> obtenerTipoArticulos() {
		return tipoArticuloRepository.findAll();
	}

}
