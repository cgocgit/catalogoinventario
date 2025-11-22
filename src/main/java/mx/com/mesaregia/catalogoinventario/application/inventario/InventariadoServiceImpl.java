package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Inventario;
import mx.com.mesaregia.catalogoinventario.repository.InventarioRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class InventariadoServiceImpl implements InventariadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventariadoServiceImpl.class);
	
	private final InventarioRepository repository;
	
	/**
	 * 
	 */
	public InventariadoServiceImpl(InventarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Inventario obtener(int idAlmacen, int idArticulo) {
		return repository.findByIdArticuloAndIdAlmacen(idArticulo, idAlmacen);
	}

	@Override
	public void actualizar(Inventario inventario) {
		Inventario inventarioEnBD = repository.findById(inventario.getIdInventario()).orElse(null);
		if (Objects.isNull(inventarioEnBD))
			throw new NotFoundException("Inventario no encontrado.");
		inventarioEnBD.setCantidadActual(inventario.getCantidadActual());
		repository.save(inventarioEnBD);
	}
	
	@Override
	public Inventario registrar(Inventario inventario) {
		LOGGER.info("Registrar inventario: {}", inventario);
		repository.saveAndFlush(inventario);
		LOGGER.info("Inventario registrado: {}", inventario);
		return inventario;
	}

	@Override
	public Collection<Inventario> obtener() {
		Collection<Inventario> lst = repository.findAll();
		lst.forEach(inv -> LOGGER.info(inv.toString()));
		return lst;
	}

}
