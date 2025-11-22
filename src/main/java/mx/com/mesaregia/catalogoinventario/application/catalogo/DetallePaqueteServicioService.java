package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.Objects;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.repository.DetallePaqueteServicioRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Service
public class DetallePaqueteServicioService implements DetallePaqueteService<DetallePaqueteServicio, Servicio> {

	private final DetallePaqueteServicioRepository repository;

	public DetallePaqueteServicioService(DetallePaqueteServicioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public DetallePaqueteServicio agregarAPaquete(Paquete paquete, Servicio v, int cantidad, double precio) {
		if (Objects.isNull(paquete))
			throw new NotFoundException("El paquete no existe o no esta disponible");
		if (Objects.isNull(v))
			throw new NotFoundException("El servicio no existe o no esta disponible");
		DetallePaqueteServicio detalle = new DetallePaqueteServicio();
		detalle.setCantidad(cantidad);
		detalle.setPaquete(paquete);
		detalle.setServicio(v);
		detalle.setTarifa(precio);
		return repository.save(detalle);
	}

	@Override
	public Collection<DetallePaqueteServicio> consultarDetalleEnPaquete(int idPaquete) {
		return repository.findByIdPaquete(idPaquete);
	}

	@Override
	public DetallePaqueteServicio quitarDelPaquete(int idDetallePaquete) throws NotFoundException {
		DetallePaqueteServicio servicioEnPaquete = repository.findById(idDetallePaquete).orElse(null);
		if (Objects.isNull(servicioEnPaquete))
			throw new NotFoundException("El detalle no se encuentra en el paquete.");
		repository.delete(servicioEnPaquete);
		return servicioEnPaquete;
	}

}
