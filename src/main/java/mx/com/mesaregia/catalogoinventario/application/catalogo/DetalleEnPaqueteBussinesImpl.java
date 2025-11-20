package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.dto.DetalleEnPaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.TipoDetalle;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class DetalleEnPaqueteBussinesImpl implements DetallePaqueteBussines {

	/**
	 * Provee DTO de Articulo
	 */
	private final ObjectProvider<DetalleEnPaqueteDTO> builderArticuloDTO;
	/**
	 * Service Paquete
	 */
	private final PaqueteService paqueteService;
	/**
	 * Service Articulo
	 */
	private final ArticuloService articuloService;
	
	private final ServicioService servicioService;
	
	/**
	 * Service transaccional detalle articulos
	 */
	private final DetallePaqueteService<DetallePaqueteArticulo, Articulo> detallePaqueteArticuloService;
	
	/**
	 * Service transaccional detalle servicio
	 */
	private final DetallePaqueteService<DetallePaqueteServicio, Servicio> detallePaqueteServicioService;
	
	private ArticuloPaquete articuloPaquete;
	
	private ServicioPaquete servicioPaquete;
	
	/**
	 * Servicio transaccional Articulos.
	 *
	 * @author Carlos Gilberto Olvera Casanova
	 * 
	 *
	 * @version 1.0.0
	 */
	private class ArticuloPaquete extends DetallePaqueteAbstract<Articulo> {

		@Override
		protected Articulo buscarElemento(int id) {
			return articuloService.obtenerArticulo(id);
		}

		@Override
		protected Paquete buscarPaquete(int id) {
			return paqueteService.obtenerPaquete(id);
		}

		@Override
		protected void validaExistencia(Paquete paquete, Articulo v) throws ItsExistException {
			Collection<DetallePaqueteArticulo> detallesEnPaquete = detallePaqueteArticuloService
					.consultarDetalleEnPaquete(paquete.getIdPaquete()); // repository.findByIdPaquete(paquete.getIdPaquete());
			DetallePaqueteArticulo servicioEncontrado = detallesEnPaquete.stream()
					.filter(s -> s.getArticulo().getIdArticulo().equals(v.getIdArticulo())).findFirst().orElse(null);
			if (Objects.nonNull(servicioEncontrado))
				throw new ItsExistException("El Servicio ya se encuentra en el paquete.");
		}

	}
	
	/**
	 * 
	 *
	 * @author Carlos Gilberto Olvera Casanova
	 * 
	 *
	 * @version 1.0.0
	 */
	private class ServicioPaquete extends DetallePaqueteAbstract<Servicio> {
		@Override
		protected Servicio buscarElemento(int id) {
			return servicioService.obtenerServicio(id);
		}
		@Override
		protected Paquete buscarPaquete(int id) {
			return paqueteService.obtenerPaquete(id);
		}
		@Override
		protected void validaExistencia(Paquete paquete, Servicio v) throws ItsExistException {
			Collection<DetallePaqueteServicio> detallesEnPaquete = detallePaqueteServicioService
					.consultarDetalleEnPaquete(paquete.getIdPaquete()); //repository.findByIdPaquete(paquete.getIdPaquete());
			DetallePaqueteServicio servicioEncontrado = detallesEnPaquete.stream()
					.filter(s -> s.getServicio().getIdServicio().equals(v.getIdServicio())).findFirst().orElse(null);
			if (Objects.nonNull(servicioEncontrado))
				throw new ItsExistException("El Servicio ya se encuentra en el paquete.");
		}
	}
	
	/**
	 * 
	 */
	public DetalleEnPaqueteBussinesImpl(ObjectProvider<DetalleEnPaqueteDTO> builderArticuloDTO,
			PaqueteService paqueteService,
			ArticuloService articuloService,
			ServicioService servicioService,
			DetallePaqueteService<DetallePaqueteArticulo, Articulo> detallePaqueteArticuloService,
			DetallePaqueteService<DetallePaqueteServicio, Servicio> detallePaqueteServicioService) {
		
		this.builderArticuloDTO = builderArticuloDTO;
		this.paqueteService = paqueteService;
		this.articuloService = articuloService;
		this.servicioService = servicioService;
		this.detallePaqueteArticuloService = detallePaqueteArticuloService;
		this.detallePaqueteServicioService = detallePaqueteServicioService;
		
		this.articuloPaquete = new ArticuloPaquete();
		this.servicioPaquete = new ServicioPaquete();
	}

	@Override
	public DetalleEnPaqueteDTO agregarAPaquete(int idPaquete, int v, int cantidad, double precio,
			TipoDetalle tipoDetalle) throws NotFoundException, ItsExistException {
		var paquete = articuloPaquete.buscarPaquete(idPaquete);
		if (TipoDetalle.Articulo.equals(tipoDetalle)) {
			var articulo = articuloPaquete.buscarElemento(v);
			articuloPaquete.validaExistencia(paquete, articulo);
			DetallePaqueteArticulo detallePaqueteArticulo = detallePaqueteArticuloService.agregarAPaquete(
					paquete, articulo, cantidad, precio);
			return getDetallePaqueteDTO(detallePaqueteArticulo);
		} else {
			var servicio = servicioPaquete.buscarElemento(v);
			servicioPaquete.validaExistencia(paquete, servicio);
			DetallePaqueteServicio detallePaqueteServicio = detallePaqueteServicioService.agregarAPaquete(
					paquete, servicio, cantidad, precio);
			return getDetallePaqueteDTO(detallePaqueteServicio);
		}
	}

	@Override
	public Collection<DetalleEnPaqueteDTO> consultarDetalleEnPaquete(int idPaquete) {
		Collection<DetallePaqueteArticulo> articulos = this.detallePaqueteArticuloService
				.consultarDetalleEnPaquete(idPaquete);
		List<DetalleEnPaqueteDTO> detalles = new ArrayList<>(articulos.stream().map(this::getDetallePaqueteDTO).toList());
		Collection<DetallePaqueteServicio> servicios = this.detallePaqueteServicioService.consultarDetalleEnPaquete(idPaquete);
		detalles.addAll(servicios.stream().map(this::getDetallePaqueteDTO).toList());
		return detalles;
	}

	@Override
	public Paquete quitarDelPaquete(int idDetallePaquete, TipoDetalle tipoDetalle) throws NotFoundException {
		if (TipoDetalle.Articulo.equals(tipoDetalle)) {
			DetallePaqueteArticulo articuloEnPaquete = this.detallePaqueteArticuloService.quitarDelPaquete(idDetallePaquete);
			return articuloEnPaquete.getPaquete();
		}
		else {
			DetallePaqueteServicio servicioEnPaquete = this.detallePaqueteServicioService.quitarDelPaquete(idDetallePaquete);
			return servicioEnPaquete.getPaquete();
		}
	}
	
	/**
	 * @param detallePaqueteArticulo
	 * @return
	 */
	private DetalleEnPaqueteDTO getDetallePaqueteDTO(DetallePaqueteArticulo detallePaqueteArticulo) {
		DetalleEnPaqueteDTO articulo = builderArticuloDTO.getObject();
		
		articulo.setIdDetallePaquete(detallePaqueteArticulo.getIdDetallePaqueteProducto());
		
		articulo.setCantidad((int)detallePaqueteArticulo.getCantidad());
		articulo.setCategoria(detallePaqueteArticulo.getArticulo().getCategoria().getNombreCategoria());
		articulo.setCodigo(detallePaqueteArticulo.getArticulo().getCodigoArticulo());
		
		articulo.setCodigoPaquete(detallePaqueteArticulo.getPaquete().getCodigoPaquete());
		
		articulo.setColor(detallePaqueteArticulo.getArticulo().getColor().getNombreColor());
		articulo.setDescripcion(detallePaqueteArticulo.getArticulo().getDescripcionArticulo());
		articulo.setIdArticuloServicio(detallePaqueteArticulo.getArticulo().getIdArticulo().intValue());
		
		articulo.setIdPaquete(detallePaqueteArticulo.getPaquete().getIdPaquete());
		
		articulo.setNombreDetalle(detallePaqueteArticulo.getArticulo().getNombreArticulo());
		articulo.setTipo(detallePaqueteArticulo.getArticulo().getTipoArticulo().getNombreTipo());
		articulo.setTipoDetalle(TipoDetalle.Articulo);
		articulo.setUnidadMedida(detallePaqueteArticulo.getArticulo().getUnidadMedida());
		
		return articulo;
	}

	private DetalleEnPaqueteDTO getDetallePaqueteDTO(DetallePaqueteServicio detallePaqueteServicio) {
		DetalleEnPaqueteDTO servicio = builderArticuloDTO.getObject();
		
		servicio.setIdDetallePaquete(detallePaqueteServicio.getIdDetallePaqueteServicio());
		
		servicio.setCantidad((int)detallePaqueteServicio.getCantidad());
		servicio.setCategoria(detallePaqueteServicio.getServicio().getCategoria().getNombreCategoria());
		servicio.setCodigo(detallePaqueteServicio.getServicio().getCodigoServicio());
		
		servicio.setCodigoPaquete(detallePaqueteServicio.getPaquete().getCodigoPaquete());
		
		servicio.setDescripcion(detallePaqueteServicio.getServicio().getDescripcion());
		servicio.setIdArticuloServicio(detallePaqueteServicio.getServicio().getIdServicio().intValue());
		
		servicio.setIdPaquete(detallePaqueteServicio.getPaquete().getIdPaquete());
		
		servicio.setNombreDetalle(detallePaqueteServicio.getServicio().getNombreServicio());
		servicio.setTipo(detallePaqueteServicio.getServicio().getTipoServicio().getDescripcion());
		servicio.setTipoDetalle(TipoDetalle.Servicio);
		
		return servicio;
	}

}
