/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import mx.com.mesaregia.catalogoinventario.api.hateoas.AbstractReflectionHateoas;
import mx.com.mesaregia.catalogoinventario.api.hateoas.CRUDMethod;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloBuilder;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloDirector;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloService;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.dto.ArticuloDTO;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller para catalogo de articulos.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/articulos")
@Tag(name = "Administrador de los Articulos", description = "Gestiona los Articulos que se tendran en el Catalogo.")
public class ArticuloController extends CommonsController {

	private final ArticuloService articuloService;

	private final ArticuloBuilder articuloBuilder;

	private final ArticuloDirector articuloDirector;

	@Qualifier("articuloControllerAssembler")
	private final AbstractReflectionHateoas<Articulo> assambler;
	
	/**
	 * 
	 */
	public ArticuloController(ArticuloService articuloService, ArticuloBuilder articuloBuilder,
			ArticuloDirector articuloDirector, AbstractReflectionHateoas<Articulo> assambler) {
		this.articuloService = articuloService;
		this.articuloBuilder = articuloBuilder;
		this.articuloDirector = articuloDirector;
		this.assambler = assambler;
	}

	/**
	 * Obtiene un articulo por su identificador si existe.
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@Operation(
			summary = "Recupera un articulo.",
			description = "Devuelve un articuñp dentro del catalogo.",
			tags = {"Busqueda"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Articulo.class)
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
	public EntityModel<Articulo> one(@Min(value = 1, message = "El valor requerido no debe ser menor a 1.") @PathVariable Integer id) {
		try {
			Articulo articulo = articuloService.obtenerArticulo(id);
			return assambler.toModel(articulo, CRUDMethod.GET);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	/**
	 * Obtiene una collecci&oacute;n de articulos. En caso de no tener elementos ser&aacute; una lista vac&iacute;a.
	 * @return
	 */
	@GetMapping()
	@Operation(
			summary = "Listado de articulos.",
			description = "Registra un articulo dentro del catalogo.",
			tags = {"Listado"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Articulo.class)
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
	public CollectionModel<EntityModel<Articulo>> getArticulos() {

		List<EntityModel<Articulo>> articulos = articuloService.obtenerArticulos().stream()
				.map(articulo -> assambler.toModel(articulo, CRUDMethod.GET)).toList();
		return CollectionModel.of(articulos);
	}

	/**
	 * Elimina un articulo por su identificador.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Eliminacion un articulo.",
			description = "Elimina un articulo dentro del catalogo.",
			tags = {"Eliminacion"},
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
	public ResponseEntity<Void> bajarArticulo(@Min(value = 1, message = "El valor minimo requerido es 1.") @PathVariable Integer id) {
		try {
			articuloService.bajarArticulo(id);
			URI allUri = linkTo(methodOn(PaqueteController.class).getPaquetes()).toUri();
			return ResponseEntity.noContent().header(org.springframework.http.HttpHeaders.LINK, "<"+allUri+">: rel=\"all\"").build();
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * Actualiza la informaci&oacute;n del articulo.
	 * 
	 * @param id
	 * @param articuloDTO
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(
			summary = "Actualiza un articulo.",
			description = "Actualiza un articulo dentro del catalogo.",
			tags = {"Actualizacion"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion del articulo que se actualizara."
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
	public EntityModel<Articulo> actualizarArticulo(@Min(value = 1, message = "El valore requerido no debe ser menor a 1.") @PathVariable Integer id,
			@NotNull @RequestBody ArticuloDTO articuloDTO) {
		try {
			construirUpdate(id, articuloDTO);
			Articulo articulo = articuloBuilder.get();
			articuloService.actualizarArticulo(articulo);
			return assambler.toModel(articulo, CRUDMethod.PUT);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * @param articuloDTO
	 */
	private void construirUpdate(Integer idArticulo, ArticuloDTO articuloDTO) {
		articuloDirector.setBuilder(articuloBuilder);
		articuloDirector.setIdArticulo(idArticulo.longValue());
		articuloDirector.setDescripcionArticulo(articuloDTO.getDescripcionArticulo());
		articuloDirector.setIdCategoria(articuloDTO.getIdCategoria());
		articuloDirector.setIdColor(articuloDTO.getIdColor());
		articuloDirector.setIdTipoArticulo(articuloDTO.getIdTipoArticulo());
		articuloDirector.setNombreArticulo(articuloDTO.getNombreArticulo());
		articuloDirector.setUnidadMedida(articuloDTO.getUnidadMedida());
		articuloDirector.setUsuarioRegistra(articuloDTO.getUsuarioRegistra());
		articuloDirector.construirArticulo();
	}

	/**
	 * Registra un articulo.
	 * 
	 * @param articuloDTO
	 * @return
	 */
	@PostMapping()
	@Operation(
			summary = "Registra un articulo.",
			description = "Registra un articulo dentro del catalogo.",
			tags = {"Registro"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
					required = true,
					useParameterTypeSchema = true,
					description = "Informacion del articulo a registrar en el catalogo."
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
	public ResponseEntity<EntityModel<Articulo>> registrarArticulo(@Valid @NotNull @RequestBody ArticuloDTO articuloDTO) {
		try {
			construirRegistro(articuloDTO);
			Articulo articulo = articuloService.registrarArticulo(articuloBuilder.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(assambler.toModel(articulo, CRUDMethod.POST));
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
	private void construirRegistro(ArticuloDTO articuloDTO) {
		articuloDirector.setBuilder(articuloBuilder);
		articuloDirector.setDescripcionArticulo(articuloDTO.getDescripcionArticulo());
		articuloDirector.setIdCategoria(articuloDTO.getIdCategoria());
		articuloDirector.setIdColor(articuloDTO.getIdColor());
		articuloDirector.setIdTipoArticulo(articuloDTO.getIdTipoArticulo());
		articuloDirector.setNombreArticulo(articuloDTO.getNombreArticulo());
		articuloDirector.setUnidadMedida(articuloDTO.getUnidadMedida());
		articuloDirector.setUsuarioRegistra(articuloDTO.getUsuarioRegistra());
		articuloDirector.construirArticulo();
	}

}
