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
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteBuilder;
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteDirector;
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteService;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/paquetes")
@Tag(name = "Administrador de Paquetes", description = "Gestiona los paquetes con sus distinto productos y servicios.")
public class PaqueteController extends CommonsController {

	private final PaqueteService paqueteService;
	private final PaqueteBuilder paqueteBuilder;
	private final PaqueteDirector paqueteDirector;
	
	/**
	 * 
	 */
	public PaqueteController(PaqueteService paqueteService, PaqueteBuilder paqueteBuilder, PaqueteDirector paqueteDirector) {
		this.paqueteService = paqueteService;
		this.paqueteBuilder = paqueteBuilder;
		this.paqueteDirector = paqueteDirector;
	}
	
	@GetMapping("/{id}")
	@Operation(
			summary = "Información del paquete.",
			description = "Proporciona información del paquete proporcionando el id del paquete.",
			tags = {"Busqueda"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Paquete.class)
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
	EntityModel<Paquete> one(@PathVariable Integer id) {
		try {
			Paquete paquete = paqueteService.obtenerPaquete(id);
			return EntityModel.of(paquete);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping()
	@Operation(
			summary = "Enlista los paquete configurados.",
			description = "Proporciona todos los paquete configurados.",
			tags = {"Listado"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Paquete.class)
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
	public CollectionModel<EntityModel<Paquete>> getPaquetes() {

		List<EntityModel<Paquete>> paquetes = paqueteService.obtenerPaquetes().stream()
				.map(articulo -> EntityModel.of(articulo)).collect(Collectors.toList());
		return CollectionModel.of(paquetes);
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Elimina un paquete.",
			description = "Elimina un paquete por su identificador.",
			tags = {"Eliminacion"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful"
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
	public void bajarPaquete(@Min(value = 1, message = "El valor minimo debe ser 1.") @PathVariable Integer id) {
		try {
			paqueteService.bajarPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	@Operation(
			summary = "Actualiza un paquete.",
			description = "Actualiza la informacion del paquete.",
			tags = {"Actualizacion"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion que se actualizara sobre el paquete existente."
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
	public EntityModel<GenericResponse> actualizarPaquete(@PathVariable int id, @NotNull(message = "Informacion requerida.") @RequestBody PaqueteDTO paqueteDTO) {
		try {
			construirUpdate(id, paqueteDTO);
			paqueteService.actualizarPaquete(paqueteBuilder.get());
			return EntityModel.of(getExito("0", "Operacion con exito", null));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * @param articuloDTO
	 */
	private void construirUpdate(Integer idPaquete, PaqueteDTO paqueteDTO) {
		paqueteDirector.setBuilder(paqueteBuilder);
		paqueteDirector.setIdPaquete(idPaquete);
		paqueteDirector.setCodigoPaquete(paqueteDTO.getCodigoPaquete());
		paqueteDirector.setNombrePaquete(paqueteDTO.getNombrePaquete());
		paqueteDirector.setPrecio(paqueteDTO.getPrecio());
		paqueteDirector.setUsuarioCreacion(paqueteDTO.getUsuarioCreacion());
		paqueteDirector.construirPaquete();
	}

	@PutMapping()
	@Operation(
			summary = "Registra un paquete.",
			description = "Registra el paquete con la informacion proporcionada bajo el esquema Paquete.",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion que se requiere para el registro del paquete."
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
	public EntityModel<GenericResponse> registrarPaquete(@NotNull(message = "Informacion requerida.") @RequestBody PaqueteDTO paqueteDTO) {
		try {
			construirRegistro(paqueteDTO);
			return EntityModel
					.of(getExito("0", "Operacion con exito", paqueteService.registrarPaquete(paqueteBuilder.get())));
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
	private void construirRegistro(PaqueteDTO paqueteDTO) {
		paqueteDirector.setBuilder(paqueteBuilder);
		paqueteDirector.setCodigoPaquete(paqueteDTO.getCodigoPaquete());
		paqueteDirector.setNombrePaquete(paqueteDTO.getNombrePaquete());
		paqueteDirector.setPrecio(paqueteDTO.getPrecio());
		paqueteDirector.setUsuarioCreacion(paqueteDTO.getUsuarioCreacion());
		paqueteDirector.construirPaquete();
	}

}
