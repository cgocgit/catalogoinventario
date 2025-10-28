package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.repository.ColorRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class ColorServiceImpl implements ColorService {

	
	private final ColorRepository colorRepository;
	
	/**
	 * 
	 */
	public ColorServiceImpl(ColorRepository colorRepository) {
		this.colorRepository = colorRepository;
	}

	@Override
	public Color obtenerColor(int idColor) {
		return colorRepository.findById(idColor).get();
	}

	@Override
	public Collection<Color> obtenerColores() {
		return colorRepository.findAll();
	}

}
