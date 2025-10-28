package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;
import java.util.Objects;

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
		repository.saveAndFlush(inventario);
		return inventario;
	}

	@Override
	public Collection<Inventario> obtener() {
		return repository.findAll();
	}

}
