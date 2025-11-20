package mx.com.mesaregia.catalogoinventario.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import mx.com.mesaregia.catalogoinventario.api.hateoas.AbstractReflectionHateoas;
import mx.com.mesaregia.catalogoinventario.api.hateoas.CRUDMethod;
import mx.com.mesaregia.catalogoinventario.application.catalogo.DetallePaqueteBussines;
import mx.com.mesaregia.catalogoinventario.application.catalogo.DetallePaqueteService;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.dto.DetalleEnPaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.DetallePaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.TipoDetalle;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/detallepaquete")
@Tag(name = "Administrador de los Articulos y Servicios en Paquetes",
description = "Gestiona los Servicios y Articulos que se tendran en el paquete.")
public class DetallePaqueteController extends CommonsController {

	private final DetallePaqueteBussines detallePaqueteBussines;
	
	@Autowired
	@Qualifier("detallePaqueteControllerAssembler")
	private AbstractReflectionHateoas<DetalleEnPaqueteDTO> assambler;
	
	public DetallePaqueteController(
			DetallePaqueteBussines detallePaqueteBussines,
			DetallePaqueteService<DetallePaqueteServicio, Servicio> detallePaqueteServicioService) {
		super();
		this.detallePaqueteBussines = detallePaqueteBussines;
	}

	@GetMapping("/{id}")
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
	public CollectionModel<EntityModel<DetalleEnPaqueteDTO>> getDetalles(
			@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable Integer id) {
		try {
			return getArticulosServicios(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/articulo")
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
	public EntityModel<DetalleEnPaqueteDTO> registrarArticulo(
			@NotNull(message = "Informacion requerida-") @RequestBody DetallePaqueteDTO params) {
		try {
			return assambler.toModel(
					detallePaqueteBussines.agregarAPaquete(params.getIdPaquete(), 
							params.getIdArticuloServicio(), params.getCantidad(), 
							params.getPrecio(), TipoDetalle.Articulo)
					, CRUDMethod.POST);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/servicio")
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
	public EntityModel<GenericResponse> registrarServicio(
			@NotNull(message = "Informacion requerida-") @RequestBody DetallePaqueteDTO params) {
		try {
			return EntityModel.of(getExito("0", "Operacion con exito.",
					detallePaqueteBussines.agregarAPaquete(params.getIdPaquete(), 
							params.getIdArticuloServicio(), params.getCantidad(), 
							params.getPrecio(), TipoDetalle.Servicio)
					));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ItsExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/articulo/{id}")
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
	public CollectionModel<EntityModel<DetalleEnPaqueteDTO>> quitarArticuloDelPaquete(
			@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable Integer id) {
		try {
			Paquete paquete = detallePaqueteBussines.quitarDelPaquete(id, TipoDetalle.Articulo);
			return getArticulosServicios(paquete.getIdPaquete());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/servicio/{id}")
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
	public CollectionModel<EntityModel<DetalleEnPaqueteDTO>> quitarServicioDelPaquete(
			@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable Integer id) {
		try {
			Paquete paquete = detallePaqueteBussines.quitarDelPaquete(id, TipoDetalle.Servicio);
			return getArticulosServicios(paquete.getIdPaquete());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * @param id
	 * @return
	 */
	private CollectionModel<EntityModel<DetalleEnPaqueteDTO>> getArticulosServicios(int id) {
		List<EntityModel<DetalleEnPaqueteDTO>> articulos = detallePaqueteBussines.consultarDetalleEnPaquete(id)
				.stream().map(detalle -> assambler.toModel(detalle, CRUDMethod.GET)).toList();
		return CollectionModel.of(articulos);
	}
}
