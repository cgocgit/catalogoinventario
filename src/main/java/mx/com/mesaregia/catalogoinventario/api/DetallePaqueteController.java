package mx.com.mesaregia.catalogoinventario.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.com.mesaregia.catalogoinventario.application.catalogo.DetallePaqueteService;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@RestController
public class DetallePaqueteController extends CommonsController {
	
	private static final Logger log = LoggerFactory.getLogger(DetallePaqueteController.class);

	private final DetallePaqueteService<DetallePaqueteArticulo, Articulo> detallePaqueteArticuloService;

	private final DetallePaqueteService<DetallePaqueteServicio, Servicio> detallePaqueteServicioService;

	public DetallePaqueteController(
			DetallePaqueteService<DetallePaqueteArticulo, Articulo> detallePaqueteArticuloService,
			DetallePaqueteService<DetallePaqueteServicio, Servicio> detallePaqueteServicioService) {
		super();
		this.detallePaqueteArticuloService = detallePaqueteArticuloService;
		this.detallePaqueteServicioService = detallePaqueteServicioService;
	}

	@GetMapping("/paquete/articulos/{id}")
	public CollectionModel<EntityModel<DetallePaqueteArticulo>> getArticulos(@PathVariable int id) {
		try {
			List<EntityModel<DetallePaqueteArticulo>> articulos = detallePaqueteArticuloService
					.consultarDetalleEnPaquete(id).stream().map(detalle -> EntityModel.of(detalle))
					.collect(Collectors.toList());
			log.info("Articulos en paquete: " + articulos.size());
			articulos.forEach(d -> log.info(d.toString()));
			return CollectionModel.of(articulos);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping("/paquete/servicios/{id}")
	public CollectionModel<EntityModel<DetallePaqueteServicio>> getServicios(@PathVariable int id) {
		try {
			List<EntityModel<DetallePaqueteServicio>> articulos = detallePaqueteServicioService
					.consultarDetalleEnPaquete(id).stream().map(detalle -> EntityModel.of(detalle))
					.collect(Collectors.toList());
			log.info("Servicios en paquete: " + articulos.size());
			articulos.forEach(d -> log.info(d.toString()));
			return CollectionModel.of(articulos);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("/paquete/articulos")
	public EntityModel<GenericResponse> registrarArticulo(@RequestBody DetallePaqueteDTO params) {
		try {
			return EntityModel.of(getExito("0", "Operacion con exito.", detallePaqueteArticuloService.agregarAPaquete(
					params.getIdPaquete(), params.getIdArticuloServicio(), params.getCantidad(), params.getPrecio())));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/paquete/servicios")
	public EntityModel<GenericResponse> registrarServicio(@RequestBody DetallePaqueteDTO params) {
		try {
			return EntityModel.of(getExito("0", "Operacion con exito.", detallePaqueteServicioService.agregarAPaquete(
					params.getIdPaquete(), params.getIdArticuloServicio(), params.getCantidad(), params.getPrecio())));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/paquete/articulos/{id}")
	public void quitarArticuloDelPaquete(@PathVariable int id) {
		try {
			detallePaqueteArticuloService.quitarDelPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/paquete/servicios/{id}")
	public void quitarSrvicioDelPaquete(@PathVariable int id) {
		try {
			detallePaqueteServicioService.quitarDelPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
