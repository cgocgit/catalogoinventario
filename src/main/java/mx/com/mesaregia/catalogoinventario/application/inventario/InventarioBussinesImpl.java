package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.domain.Inventario;
import mx.com.mesaregia.catalogoinventario.dto.ExistenciaArticuloDTO;
import mx.com.mesaregia.catalogoinventario.dto.InventarioDTO;
import mx.com.mesaregia.catalogoinventario.dto.RegistroInventarioDTO;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class InventarioBussinesImpl implements InventarioBussines {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventarioBussinesImpl.class);
	
	private final ObjectProvider<InventarioDTO> providerInventarioDTO;
	
	private final ObjectProvider<ExistenciaArticuloDTO> providerExistenciaArticuloDTO;
	
	private final InventarioService inventarioService;
	
	/**
	 * 
	 */
	public InventarioBussinesImpl(InventarioService inventarioService, ObjectProvider<InventarioDTO> providerInventarioDTO
			, ObjectProvider<ExistenciaArticuloDTO> providerExistenciaArticuloDTO) {
		this.inventarioService = inventarioService;
		this.providerInventarioDTO = providerInventarioDTO;
		this.providerExistenciaArticuloDTO = providerExistenciaArticuloDTO;
	}

	@Override
	public ExistenciaArticuloDTO registrarInventario(RegistroInventarioDTO registroInventario) {
		 ExistenciaArticulo existenciaArticulo = inventarioService.agregarArticulo(registroInventario.getIdInventario(), registroInventario.getIdArticulo(), registroInventario.getCodigoUnidad());
		 return getDTO(existenciaArticulo);
	}
	
	@Override
	public Collection<InventarioDTO> consultarInventario(int idAlmacen) {
		LOGGER.info("Consultado inventario en almacen: {}", idAlmacen);
		Collection<Inventario> inventarios = inventarioService.consultarInventario();
		return inventarios.stream()
				.map(this::getDTO).toList();
	}
	
	private InventarioDTO getDTO(Inventario inventario) {
		
		InventarioDTO inventarioDTO = providerInventarioDTO.getObject();
		Almacen almacen = inventario.getAlmacen();
		Articulo articulo = inventario.getArticulo();
		
		inventarioDTO.setCantidadActual(inventario.getCantidadActual());
		inventarioDTO.setDescripcionArticulo(articulo.getDescripcionArticulo());
		inventarioDTO.setFechaActualizacion(inventario.getFechaActualizacion());
		inventarioDTO.setIdAlmacen(almacen.getIdAlmacen());
		inventarioDTO.setIdArticulo(articulo.getIdArticulo());
		inventarioDTO.setIdInventario(inventario.getIdInventario());
		inventarioDTO.setNombreAlmacen(almacen.getNombreAlmacen());
		inventarioDTO.setNombreArticulo(articulo.getNombreArticulo());
		inventarioDTO.setStockMaximo(inventario.getStockMaximo());
		inventarioDTO.setStockMinimo(inventario.getStockMinimo());
		inventarioDTO.setUsuarioActualizacion(inventario.getUsuarioActualizacion());
		
		return inventarioDTO;
	}
	

	/**
	 * @param existenciaArticulo
	 * @return
	 */
	private ExistenciaArticuloDTO getDTO(ExistenciaArticulo existenciaArticulo) {
		ExistenciaArticuloDTO existenciaArticuloDTO = providerExistenciaArticuloDTO.getObject();
		
		Almacen almacen = existenciaArticulo.getAlmacen();
		Articulo articulo = existenciaArticulo.getArticulo();
		
		existenciaArticuloDTO.setDescripcionArticulo(articulo.getDescripcionArticulo());
		existenciaArticuloDTO.setEstado(existenciaArticulo.getEstado());
		existenciaArticuloDTO.setIdAlmacen(almacen.getIdAlmacen());
		existenciaArticuloDTO.setIdArticulo(articulo.getIdArticulo());
		existenciaArticuloDTO.setNombreAlmacen(almacen.getNombreAlmacen());
		existenciaArticuloDTO.setNombreArticulo(articulo.getNombreArticulo());
				
		return existenciaArticuloDTO;
	}

}
