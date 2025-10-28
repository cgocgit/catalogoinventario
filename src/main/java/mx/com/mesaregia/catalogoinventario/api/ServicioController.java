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
	
	
	@GetMapping("/servicios/{id}")
	EntityModel<Servicio> one(@PathVariable Integer id) {
		try {
			Servicio servicio = servicioService.obtenerServicio(id); // servicioRepository.findById(id).get();
			return EntityModel.of(servicio);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	@GetMapping("/servicios")
	public CollectionModel<EntityModel<Servicio>> getArticulos() {

		List<EntityModel<Servicio>> servicios = servicioService.obtenerServicios() //servicioRepository.findAll()
				.stream().map(servicio -> EntityModel.of(servicio)).collect(Collectors.toList());
		return CollectionModel.of(servicios);
	}
	
	@DeleteMapping("/servicios/{id}")
	public void bajarArticulo(@PathVariable Integer id) {
		try {
			servicioService.bajarServicio(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PatchMapping("/servicios/{id}")
	public EntityModel<GenericResponse> actualizarServicios(@PathVariable int id, @RequestBody ServicioDTO articuloDTO) {
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

	@PutMapping("/servicios")
	public EntityModel<GenericResponse> registrarServicios(@RequestBody ServicioDTO articuloDTO) {
		try {
			construirRegistro(articuloDTO);
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
