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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	@GetMapping("/paquetes/{id}")
	EntityModel<Paquete> one(@PathVariable Integer id) {
		try {
			Paquete paquete = paqueteService.obtenerPaquete(id);
			return EntityModel.of(paquete);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/paquetes")
	public CollectionModel<EntityModel<Paquete>> getArticulos() {

		List<EntityModel<Paquete>> paquetes = paqueteService.obtenerPaquetes().stream()
				.map(articulo -> EntityModel.of(articulo)).collect(Collectors.toList());
		return CollectionModel.of(paquetes);
	}

	@DeleteMapping("/paquetes/{id}")
	public void bajarArticulo(@PathVariable Integer id) {
		try {
			paqueteService.bajarPaquete(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PatchMapping("/paquetes/{id}")
	public EntityModel<GenericResponse> actualizarArticulo(@PathVariable int id, @RequestBody PaqueteDTO paqueteDTO) {
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

	@PutMapping("/paquetes")
	public EntityModel<GenericResponse> registrarArticulo(@RequestBody PaqueteDTO paqueteDTO) {
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
