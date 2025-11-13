package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.repository.ServicioRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Service
public class ServicioServiceImpl implements ServicioService {
	
	private static final Logger log = LoggerFactory.getLogger(ServicioServiceImpl.class);

	private final ServicioRepository servicioRepository;
	
	private final String ARTICULONOENCONTRADO = "El articulo no se ha encontrado."; 

	/**
	 * 
	 */
	public ServicioServiceImpl(ServicioRepository servicioRepository) {
		this.servicioRepository = servicioRepository;
	}

	@Override
	public Collection<Servicio> obtenerServicios() {
		return servicioRepository.findByActivoTrue(); //.findAll();
	}

	@Override
	public Servicio obtenerServicio(Integer idArticulo) throws NotFoundException {
		return getServicio(idArticulo.longValue());
	}

	/**
	 * @param idArticulo
	 * @return
	 */
	private Servicio getServicio(@NotNull @Min(value = 1) Long idArticulo) {
		return servicioRepository.findByIdServicioAndActivoTrue(idArticulo.intValue()) // .findById(idArticulo.intValue())
				.orElseThrow(() -> new NotFoundException(idArticulo.toString()));
	}

	@Override
	public void actualizarServicio(Servicio articulo) throws NotFoundException {
		Servicio articuloDeRepositorio = getServicio(articulo.getIdServicio().longValue());
		trasladarDatos(articulo, articuloDeRepositorio);
		servicioRepository.save(articulo);
	}

	/**
	 * @param articulo
	 * @param articuloDeRepositorio
	 */
	private void trasladarDatos(Servicio articulo, Servicio articuloDeRepositorio) {
		articuloDeRepositorio.setCategoria(articulo.getCategoria());
		articuloDeRepositorio.setCodigoServicio(articulo.getCodigoServicio());
		articuloDeRepositorio.setCosto(articulo.getCosto());
		articuloDeRepositorio.setDescripcion(articulo.getDescripcion());
		articuloDeRepositorio.setNombreServicio(articulo.getNombreServicio());
		articuloDeRepositorio.setTarifaBase(articulo.getTarifaBase());
		articuloDeRepositorio.setTipoServicio(articulo.getTipoServicio());		
	}

	@Override
	public void bajarServicio(Integer idArticulo) throws NotFoundException {
		Servicio articulo = getServicio(idArticulo.longValue());
		if (Objects.isNull(articulo))
			throw new NotFoundException(ARTICULONOENCONTRADO);
		articulo.setActivo(false);
		servicioRepository.save(articulo);
	}

	@Override
	public Servicio registrarServicio(Servicio articulo) {
		articulo.setActivo(true);
		articulo.setIdServicio(null);
		log.info(articulo.toString());
		servicioRepository.save(articulo);
		return articulo;
	}

}
