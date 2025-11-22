package mx.com.mesaregia.catalogoinventario.api;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import mx.com.mesaregia.catalogoinventario.application.inventario.InventarioBussines;
import mx.com.mesaregia.catalogoinventario.application.inventario.InventarioService;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.dto.ExistenciaArticuloDTO;
import mx.com.mesaregia.catalogoinventario.dto.InventarioDTO;
import mx.com.mesaregia.catalogoinventario.dto.RegistroInventarioDTO;

/**
 * Controllador para la administración del inventario.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/inventario")
@Tag(name = "Administrador de Inventario", description = "Gestiona el inventario de Articulos.")
public class InventarioController extends CommonsController {

	private final InventarioBussines inventarioBussines;
	private final InventarioService inventarioService;
	
	/**
	 * 
	 */
	public InventarioController(InventarioService inventarioService, InventarioBussines inventarioBussines) {
		this.inventarioService = inventarioService;
		this.inventarioBussines = inventarioBussines;
	}
	
	@PostMapping()
	@Operation(
			summary = "Registra un articulo.",
			description = "Actualiza el inventario con el registro del articulo..",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion del articulo a registrar en el inventario."
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
	ResponseEntity<EntityModel<ExistenciaArticuloDTO>> registrarInventario(@NotNull @RequestBody RegistroInventarioDTO inventario) {
		try {
			ExistenciaArticuloDTO existenciaArticuloDTO = inventarioBussines.registrarInventario(inventario);
			EntityModel<ExistenciaArticuloDTO> model = EntityModel.of(existenciaArticuloDTO);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	@Operation(
			summary = "Lista articulos.",
			description = "Enlista los articulos en el inventario.",
			tags = {"Listado"},
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
	public CollectionModel<EntityModel<InventarioDTO>> obtenerInventario() {
		List<EntityModel<InventarioDTO>> invetario = inventarioBussines.consultarInventario(0).stream()
				.map(EntityModel::of).toList();
		return CollectionModel.of(invetario);
	}
	
	@PutMapping("/{id}")
	@Operation(
			summary = "Actualiza inventario.",
			description = "Actualiza el estatus del articulo en el inventario..",
			tags = {"Actualizacion"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "El estatus que se actaulizara en el inventario."
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
	public EntityModel<GenericResponse> actualizarInventario(@Min(value = 1) @PathVariable Integer id,
			@NotNull @RequestBody EstadoArticulo estado) {
		try {
			inventarioService.actualizarEstadoArticulo(id, estado);
			return EntityModel.of(getExito("0", "Actualizacion realizada", null));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
