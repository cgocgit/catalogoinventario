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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/paquetes")
@Tag(name = "Administrador de los Articulos y Servicios en Paquetes",
description = "Gestiona los Servicios y Articulos que se tendran en el paquete.")
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

	@GetMapping("/articulos/{id}")
	@Operation(
			summary = "Listado de articulos.",
			description = "Lista los articulos dentro del paquete.",
			tags = {"Listado"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = DetallePaqueteArticulo.class)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public CollectionModel<EntityModel<DetallePaqueteArticulo>> getArticulos(@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable int id) {
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

	@GetMapping("/servicios/{id}")
	@Operation(
			summary = "Listado de servicios.",
			description = "Enlista los servicios dentro del paquete.",
			tags = {"Listado"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = DetallePaqueteServicio.class)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public CollectionModel<EntityModel<DetallePaqueteServicio>> getServicios(@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable int id) {
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

	@PutMapping("/articulos")
	@Operation(
			summary = "Registra un servicio.",
			description = "Registra un servicio dentro del paquete.",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Se registrara el Articulo sobre el paquete."
			),
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = GenericResponse.class)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public EntityModel<GenericResponse> registrarArticulo(@NotNull(message = "Informacion requerida-") @RequestBody DetallePaqueteDTO params) {
		try {
			return EntityModel.of(getExito("0", "Operacion con exito.", detallePaqueteArticuloService.agregarAPaquete(
					params.getIdPaquete(), params.getIdArticuloServicio(), params.getCantidad(), params.getPrecio())));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/servicios")
	@Operation(
			summary = "Registra un servicio.",
			description = "Registra un servicio dentro del paquete.",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Se registrara el servicio sobre el paquete existente existente."
			),
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = GenericResponse.class)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public EntityModel<GenericResponse> registrarServicio(@NotNull(message = "Informacion requerida-") @RequestBody DetallePaqueteDTO params) {
		try {
			return EntityModel.of(getExito("0", "Operacion con exito.", detallePaqueteServicioService.agregarAPaquete(
					params.getIdPaquete(), params.getIdArticuloServicio(), params.getCantidad(), params.getPrecio())));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/articulos/{id}")
	@Operation(
			summary = "Elimina el articulo.",
			description = "Elimina el articulo en el paquete.",
			tags = {"Eliminacion"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json"
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public void quitarArticuloDelPaquete(@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable int id) {
		try {
			detallePaqueteArticuloService.quitarDelPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/servicios/{id}")
	@Operation(
			summary = "Elimina el servicio.",
			description = "Elimina el servicio en el paquete.",
			tags = {"Eliminacion"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json"
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "Not Found",
							content = @Content(
									mediaType = "application/json"
									)
							)
			}
			)
	public void quitarSrvicioDelPaquete(@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable int id) {
		try {
			detallePaqueteServicioService.quitarDelPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
