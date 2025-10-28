package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.repository.ArticuloRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Service
public class ArticuloServiceImpl implements ArticuloService {
	
	private static final Logger log = LoggerFactory.getLogger(ArticuloServiceImpl.class);

	private final ArticuloRepository articuloRepository;
	
	private final String ARTICULONOENCONTRADO = "El articulo no se ha encontrado."; 

	/**
	 * 
	 */
	public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
		this.articuloRepository = articuloRepository;
	}

	@Override
	public Collection<Articulo> obtenerArticulos() {
		return articuloRepository.findAll();
	}

	@Override
	public Articulo obtenerArticulo(Integer idArticulo) throws NotFoundException {
		return getArticulo(idArticulo.longValue());
	}

	/**
	 * @param idArticulo
	 * @return
	 */
	private Articulo getArticulo(@NotNull @Min(value = 1) Long idArticulo) {
		return articuloRepository.findById(idArticulo.intValue())
				.orElseThrow(() -> new NotFoundException(idArticulo.toString()));
	}

	@Override
	public void actualizarArticulo(Articulo articulo) throws NotFoundException {
		Articulo articuloDeRepositorio = getArticulo(articulo.getIdArticulo());
		trasladarDatos(articulo, articuloDeRepositorio);
		articuloRepository.save(articulo);
	}

	/**
	 * @param articulo
	 * @param articuloDeRepositorio
	 */
	private void trasladarDatos(Articulo articulo, Articulo articuloDeRepositorio) {
		articuloDeRepositorio.setCategoria(articulo.getCategoria());
		articuloDeRepositorio.setColor(articulo.getColor());
		articuloDeRepositorio.setDescripcionArticulo(articulo.getDescripcionArticulo());
		articuloDeRepositorio.setTipoArticulo(articulo.getTipoArticulo());
		articuloDeRepositorio.setUnidadMedida(articulo.getUnidadMedida());
	}

	@Override
	public void bajarArticulo(Integer idArticulo) throws NotFoundException {
		Articulo articulo = getArticulo(idArticulo.longValue());
		if (Objects.isNull(articulo))
			throw new NotFoundException(ARTICULONOENCONTRADO);
		articulo.setActivo(false);
		articuloRepository.save(articulo);
	}

	@Override
	public Articulo registrarArticulo(Articulo articulo) {
		articulo.setActivo(true);
		articulo.setIdArticulo(null);
		log.info(articulo.toString());
		articuloRepository.save(articulo);
		return articulo;
	}

}
