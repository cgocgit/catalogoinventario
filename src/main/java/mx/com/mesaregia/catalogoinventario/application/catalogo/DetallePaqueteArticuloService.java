package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
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
public class DetallePaqueteArticuloService extends DetallePaqueteAbstract<Articulo>
		implements DetallePaqueteService<DetallePaqueteArticulo, Articulo> {

	private final PaqueteService paqueteService;
	private final ArticuloService articuloService;

	private final DetallePaqueteArticuloRepository repository;

	public DetallePaqueteArticuloService(PaqueteService paqueteService, ArticuloService aticuloService,
			DetallePaqueteArticuloRepository repository) {
		super();
		this.paqueteService = paqueteService;
		this.articuloService = aticuloService;
		this.repository = repository;
	}

	@Override
	public DetallePaqueteArticulo agregarAPaquete(Paquete paquete, Articulo v, int cantidad, double precio) {
		if (Objects.isNull(paquete))
			throw new NotFoundException("El paquete no existe o no esta disponible");
		if (Objects.isNull(v))
			throw new NotFoundException("El servicio no existe o no esta disponible");
		validaExistencia(paquete, v);
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
	public void quitarDelPaquete(int idDetallePaquete) throws NotFoundException {
		DetallePaqueteArticulo servicioEnPaquete = repository.findById(idDetallePaquete).orElse(null);
		if (Objects.isNull(servicioEnPaquete))
			throw new NotFoundException("El detalle no se encuentra en el paquete.");
		repository.delete(servicioEnPaquete);
	}

	@Override
	protected Articulo buscarElemento(int id) {
		return articuloService.obtenerArticulo(id);
	}

	@Override
	protected Paquete buscarPaquete(int id) {
		return paqueteService.obtenerPaquete(id);
	}

	@Override
	public DetallePaqueteArticulo agregarAPaquete(int idPaquete, int v, int cantidad, double precio) {
		return agregarAPaquete(buscarPaquete(idPaquete), buscarElemento(v), cantidad, precio);
	}

	@Override
	protected void validaExistencia(Paquete paquete, Articulo v) throws ItsExistException {
		List<DetallePaqueteArticulo> detallesEnPaquete = repository.findByIdPaquete(paquete.getIdPaquete());
		DetallePaqueteArticulo servicioEncontrado = detallesEnPaquete.stream()
				.filter(s -> s.getArticulo().getIdArticulo().equals(v.getIdArticulo())).findFirst().orElse(null);
		if (Objects.nonNull(servicioEncontrado))
			throw new ItsExistException("El Servicio ya se encuentra en el paquete.");
	}

}
