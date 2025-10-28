package mx.com.mesaregia.catalogoinventario.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.com.mesaregia.catalogoinventario.application.inventario.InventarioService;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.domain.Inventario;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@RestController
public class InventarioController extends CommonsController {

	private final InventarioService inventarioService;
	
	/**
	 * 
	 */
	public InventarioController(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}
	
	
	@PutMapping("/inventario")
	EntityModel<GenericResponse> registrarInventario(@RequestBody RegistroInventarioDTO inventario) {
		try {
			ExistenciaArticulo existenciaArticulo = inventarioService.agregarArticulo(inventario.getIdInventario(), inventario.getIdArticulo(), inventario.getCodigoUnidad());
			return EntityModel.of(getExito("0", "Operacion con exito", existenciaArticulo));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/inventario")
	public CollectionModel<EntityModel<Inventario>> obtenerInventario() {
		List<EntityModel<Inventario>> invetario = inventarioService.consultarInventario().stream().map(inv -> EntityModel.of(inv)).collect(Collectors.toList());
		return CollectionModel.of(invetario);
	}
	
	@PatchMapping("/inventario/{id}")
	public EntityModel<GenericResponse> actualizarInventario(@PathVariable int id, @RequestBody EstadoArticulo estado) {
		try {
			inventarioService.actualizarEstadoArticulo(id, estado);
			return EntityModel.of(getExito("0", "Actualizacion realizada", null));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
