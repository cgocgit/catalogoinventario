package mx.com.mesaregia.catalogoinventario.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import mx.com.mesaregia.catalogoinventario.api.hateoas.AbstractReflectionHateoas;
import mx.com.mesaregia.catalogoinventario.api.hateoas.CRUDMethod;
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteBuilder;
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteDirector;
import mx.com.mesaregia.catalogoinventario.application.catalogo.PaqueteService;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.dto.PaqueteDTO;

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
	
	@Autowired
	@Qualifier("paqueteControllerAssembler")
	private AbstractReflectionHateoas<Paquete> assambler;

	/**
	 * 
	 */
	public PaqueteController(PaqueteService paqueteService, PaqueteBuilder paqueteBuilder,
			PaqueteDirector paqueteDirector) {
		this.paqueteService = paqueteService;
		this.paqueteBuilder = paqueteBuilder;
		this.paqueteDirector = paqueteDirector;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Información del paquete.", description = "Proporciona información del paquete proporcionando el id del paquete.", tags = {
			"Busqueda" }, responses = {
					@ApiResponse(responseCode = "200", description = "Successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paquete.class))),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")) })
	public EntityModel<Paquete> one(@PathVariable Integer id) {
		try {
			Paquete paquete = paqueteService.obtenerPaquete(id);
			return assambler.toModel(paquete, CRUDMethod.GET);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping()
	@Operation(summary = "Enlista los paquete configurados.", description = "Proporciona todos los paquete configurados.", tags = {
			"Listado" }, responses = {
					@ApiResponse(responseCode = "200", description = "Successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paquete.class))),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")) })
	public CollectionModel<EntityModel<Paquete>> getPaquetes() {

		List<EntityModel<Paquete>> paquetes = paqueteService.obtenerPaquetes().stream()
				.map(paquete -> assambler.toModel(paquete, CRUDMethod.GET)).collect(Collectors.toList());
		return CollectionModel.of(paquetes);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un paquete.", description = "Elimina un paquete por su identificador.", tags = {
			"Eliminacion" }, responses = { @ApiResponse(responseCode = "200", description = "Successful"),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Void> bajarPaquete(@Min(value = 1, message = "El valor minimo debe ser 1.") @PathVariable Integer id) {
		try {
			paqueteService.bajarPaquete(id);
			URI allUri = linkTo(methodOn(PaqueteController.class).getPaquetes()).toUri();
			return ResponseEntity.noContent().header(org.springframework.http.HttpHeaders.LINK, "<"+allUri+">: rel=\"all\"").build();
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualiza un paquete.", description = "Actualiza la informacion del paquete.", tags = {
			"Actualizacion" }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, useParameterTypeSchema = true, description = "Informacion que se actualizara sobre el paquete existente."), responses = {
					@ApiResponse(responseCode = "200", description = "Successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")) })
	public EntityModel<Paquete> actualizarPaquete(@PathVariable Integer id,
			@NotNull(message = "Informacion requerida.") @RequestBody PaqueteDTO paqueteDTO) {
		try {
			construirUpdate(id, paqueteDTO);
			Paquete paquete = paqueteBuilder.get();
			paqueteService.actualizarPaquete(paquete);
			return assambler.toModel(paquete, CRUDMethod.PUT);
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

	@PostMapping()
	@Operation(summary = "Registra un paquete.", description = "Registra el paquete con la informacion proporcionada bajo el esquema Paquete.", tags = {
			"Registro" }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, useParameterTypeSchema = true, description = "Informacion que se requiere para el registro del paquete."), responses = {
					@ApiResponse(responseCode = "200", description = "Successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
					@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<EntityModel<Paquete>> registrarPaquete(
			@NotNull(message = "Informacion requerida.") @RequestBody PaqueteDTO paqueteDTO) {
		try {
			construirRegistro(paqueteDTO);
			Paquete paquete = paqueteService.registrarPaquete(paqueteBuilder.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(assambler.toModel(paquete, CRUDMethod.POST));
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
