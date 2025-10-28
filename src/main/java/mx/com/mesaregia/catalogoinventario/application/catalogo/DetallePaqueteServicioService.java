package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
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
public class DetallePaqueteServicioService extends DetallePaqueteAbstract<Servicio>
		implements DetallePaqueteService<DetallePaqueteServicio, Servicio> {

	private final PaqueteService paqueteService;
	private final ServicioService servicioService;

	private final DetallePaqueteServicioRepository repository;

	public DetallePaqueteServicioService(PaqueteService paqueteService, ServicioService servicioService,
			DetallePaqueteServicioRepository repository) {
		super();
		this.paqueteService = paqueteService;
		this.servicioService = servicioService;
		this.repository = repository;
	}

	@Override
	public DetallePaqueteServicio agregarAPaquete(Paquete paquete, Servicio v, int cantidad, double precio) {
		if (Objects.isNull(paquete))
			throw new NotFoundException("El paquete no existe o no esta disponible");
		if (Objects.isNull(v))
			throw new NotFoundException("El servicio no existe o no esta disponible");
		validaExistencia(paquete, v);
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
	public void quitarDelPaquete(int idDetallePaquete) throws NotFoundException {
		DetallePaqueteServicio servicioEnPaquete = repository.findById(idDetallePaquete).orElse(null);
		if (Objects.isNull(servicioEnPaquete))
			throw new NotFoundException("El detalle no se encuentra en el paquete.");
		repository.delete(servicioEnPaquete);
	}

	@Override
	protected Servicio buscarElemento(int id) {
		return servicioService.obtenerServicio(id);
	}

	@Override
	protected Paquete buscarPaquete(int id) {
		return paqueteService.obtenerPaquete(id);
	}

	@Override
	public DetallePaqueteServicio agregarAPaquete(int idPaquete, int v, int cantidad, double precio) {
		return agregarAPaquete(buscarPaquete(idPaquete), buscarElemento(v), cantidad, precio);
	}

	@Override
	protected void validaExistencia(Paquete paquete, Servicio v) throws ItsExistException {
		List<DetallePaqueteServicio> detallesEnPaquete = repository.findByIdPaquete(paquete.getIdPaquete());
		DetallePaqueteServicio servicioEncontrado = detallesEnPaquete.stream()
				.filter(s -> s.getServicio().getIdServicio().equals(v.getIdServicio())).findFirst().orElse(null);
		if (Objects.nonNull(servicioEncontrado))
			throw new ItsExistException("El Servicio ya se encuentra en el paquete.");
	}

}
