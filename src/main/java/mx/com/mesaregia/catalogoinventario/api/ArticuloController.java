/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloBuilder;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloDirector;
import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloService;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;

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

/**
 * 
 */
@RestController
public class ArticuloController extends CommonsController {

	private final ArticuloService articuloService;

	private final ArticuloBuilder articuloBuilder;

	private final ArticuloDirector articuloDirector;

	/**
	 * 
	 */
	public ArticuloController(ArticuloService articuloService, ArticuloBuilder articuloBuilder,
			ArticuloDirector articuloDirector) {
		this.articuloService = articuloService;
		this.articuloBuilder = articuloBuilder;
		this.articuloDirector = articuloDirector;
	}

	@GetMapping("/articulos/{id}")
	EntityModel<Articulo> one(@PathVariable Integer id) {
		try {
			Articulo articulo = articuloService.obtenerArticulo(id);
			return EntityModel.of(articulo);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/articulos")
	public CollectionModel<EntityModel<Articulo>> getArticulos() {

		List<EntityModel<Articulo>> articulos = articuloService.obtenerArticulos().stream()
				.map(articulo -> EntityModel.of(articulo)).collect(Collectors.toList());
		return CollectionModel.of(articulos);
	}

	@DeleteMapping("/articulos/{id}")
	public void bajarArticulo(@PathVariable Integer id) {
		try {
			articuloService.bajarArticulo(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PatchMapping("/articulos/{id}")
	public EntityModel<GenericResponse> actualizarArticulo(@PathVariable int id, @RequestBody ArticuloDTO articuloDTO) {
		try {
			construirUpdate(id, articuloDTO);
			articuloService.actualizarArticulo(articuloBuilder.get());
			return EntityModel.of(getExito("0", "Operacion con exito", null));
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

	@PutMapping("/articulos")
	public EntityModel<GenericResponse> registrarArticulo(@RequestBody ArticuloDTO articuloDTO) {
		try {
			construirRegistro(articuloDTO);
			return EntityModel
					.of(getExito("0", "Operacion con exito", articuloService.registrarArticulo(articuloBuilder.get())));
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
