package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.repository.DetallePaqueteArticuloRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Service
public class DetallePaqueteArticuloService 
		implements DetallePaqueteService<DetallePaqueteArticulo, Articulo> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DetallePaqueteArticuloService.class);

	private final DetallePaqueteArticuloRepository repository;
	
	public DetallePaqueteArticuloService(PaqueteService paqueteService, ArticuloService aticuloService,
			DetallePaqueteArticuloRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public DetallePaqueteArticulo agregarAPaquete(Paquete paquete, Articulo v, int cantidad, double precio) {
		if (Objects.isNull(paquete))
			throw new NotFoundException("El paquete no existe o no esta disponible");
		if (Objects.isNull(v))
			throw new NotFoundException("El servicio no existe o no esta disponible");
//		validaExistencia(paquete, v);
		DetallePaqueteArticulo detalle = new DetallePaqueteArticulo();
		detalle.setCantidad(cantidad);
		detalle.setPaquete(paquete);
		detalle.setArticulo(v);
		detalle.setPrecioUnitario(precio);
		return repository.save(detalle);
	}

	@Override
	public Collection<DetallePaqueteArticulo> consultarDetalleEnPaquete(int idPaquete) {
		return repository.findByIdPaquete(idPaquete);
	}

	@Override
	public DetallePaqueteArticulo quitarDelPaquete(int idDetallePaquete) throws NotFoundException {
		DetallePaqueteArticulo articuloEnPaquete = repository.findById(idDetallePaquete).orElse(null);
		if (Objects.isNull(articuloEnPaquete))
			throw new NotFoundException("El detalle no se encuentra en el paquete.");
		repository.delete(articuloEnPaquete);
		LOGGER.info(articuloEnPaquete.toString());
		return articuloEnPaquete;
	}


//	@Deprecated
//	@Override
//	public DetallePaqueteArticulo agregarAPaquete(int idPaquete, int v, int cantidad, double precio) {
//		return null;
//	}


}
