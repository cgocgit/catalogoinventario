package mx.com.mesaregia.catalogoinventario.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import mx.com.mesaregia.catalogoinventario.application.catalogo.ServicioBuilder;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ServicioDirector;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ServicioService;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/servicios")
@Tag(name = "Administrador de Servicios", description = "Gestiona los Servicios que se tendran en catalogo.")
public class ServicioController extends CommonsController {

	private final ServicioService servicioService;
	private final ServicioDirector servicioDirector;
	private final ServicioBuilder servicioBuilder;
	
	/**
	 * 
	 */
	public ServicioController(ServicioService servicioService, ServicioDirector servicioDirector, ServicioBuilder servicioBuilder) {
		super();
		/* Constructor principal */
		this.servicioService = servicioService;
		this.servicioDirector = servicioDirector;
		this.servicioBuilder = servicioBuilder;
	}
	
	
	@GetMapping("/{id}")
	@Operation(
			summary = "Proporciona el servicio.",
			description = "Proporcionara el servicio buscado por su identificador si es que existe.",
			tags = {"Busqueda"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Servicio.class)
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
	EntityModel<Servicio> one(@Min(value = 1, message = "El identificador no debe ser menor a 1.") @PathVariable Integer id) {
		try {
			Servicio servicio = servicioService.obtenerServicio(id); // servicioRepository.findById(id).get();
			return EntityModel.of(servicio);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	@GetMapping()
	@Operation(
			summary = "Lista los Servicios.",
			description = "Lista los Servicio en el catalogo.",
			tags = {"Listado"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Servicio.class)
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
	public CollectionModel<EntityModel<Servicio>> getServicios() {
		List<EntityModel<Servicio>> servicios = servicioService.obtenerServicios() //servicioRepository.findAll()
				.stream().map(servicio -> EntityModel.of(servicio)).collect(Collectors.toList());
		return CollectionModel.of(servicios);
	}
	
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Elimina un Servicio.",
			description = "Elimina el Servicio por su identificador.",
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
	public void bajarServicio(@Min(value = 1, message = "El identificador no es menor a 1-") @PathVariable Integer id) {
		try {
			servicioService.bajarServicio(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	@Operation(
			summary = "Actualiza un Servicio.",
			description = "Actualiza la informacion del Servicio.",
			tags = {"Actualizacion"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion que se actualizara sobre el Servicio existente."
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
	public EntityModel<GenericResponse> actualizarServicios(@Min(value = 1, message = "El identificador no es menor a 1.") @PathVariable int id, 
			@NotNull(message = "Informacion requerida.") @RequestBody ServicioDTO articuloDTO) {
		try {
			construirUpdate(id, articuloDTO);
			servicioService.actualizarServicio(servicioBuilder.get());
			return EntityModel.of(getExito("0", "Operacion con exito", null));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * @param articuloDTO
	 */
	private void construirUpdate(Integer idServicio, ServicioDTO servicioDTO) {
		servicioDirector.setBuilder(servicioBuilder);
		servicioBuilder.preparar();
		servicioDirector.setIdServicio(idServicio);
	
		servicioDirector.setCodigoServicio(servicioDTO.getCodigoServicio());
		servicioDirector.setCosto(servicioDTO.getCosto());
		servicioDirector.setDescripcion(servicioDTO.getDescripcion());
		servicioDirector.setIdCategoria(servicioDTO.getIdCategoria());
		servicioDirector.setNombreServicio(servicioDTO.getNombreServicio());
		servicioDirector.setTarifaBase(servicioDTO.getTarifaBase());
		servicioDirector.setTipoServicio(servicioDTO.getTipoServicio());
		servicioDirector.construirServicio();
	}

	@PutMapping("")
	@Operation(
			summary = "Registra un Servicio.",
			description = "Registra la información del servicio.",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion que se requiere para el registro del Servicio.."
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
	public EntityModel<GenericResponse> registrarServicios(@NotNull(message = "Informacion requerida.") @RequestBody ServicioDTO servicioDTO) {
		try {
			construirRegistro(servicioDTO);
			return EntityModel
					.of(getExito("0", "Operacion con exito", servicioService.registrarServicio(servicioBuilder.get())));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * @param nombreArticulo
	 * @param descripcionArticulo
	 * @param usuarioRegistra
	 * @param unidadMedida
	 * @param idCategoria
	 * @param idColor
	 * @param idTipoArticulo
	 */
	private void construirRegistro(ServicioDTO servicioDTO) {
		servicioDirector.setBuilder(servicioBuilder);
		servicioDirector.setBuilder(servicioBuilder);
		servicioBuilder.preparar();
	
		servicioDirector.setCodigoServicio(servicioDTO.getCodigoServicio());
		servicioDirector.setCosto(servicioDTO.getCosto());
		servicioDirector.setDescripcion(servicioDTO.getDescripcion());
		servicioDirector.setIdCategoria(servicioDTO.getIdCategoria());
		servicioDirector.setNombreServicio(servicioDTO.getNombreServicio());
		servicioDirector.setTarifaBase(servicioDTO.getTarifaBase());
		servicioDirector.setTipoServicio(servicioDTO.getTipoServicio());
		servicioDirector.construirServicio();
	}


}
