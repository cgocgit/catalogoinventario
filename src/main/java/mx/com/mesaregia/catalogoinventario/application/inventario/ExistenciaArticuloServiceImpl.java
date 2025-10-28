package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;
import java.util.Objects;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.repository.ExistenciaArticuloRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class ExistenciaArticuloServiceImpl implements ExistenciaArticuloService {

	private final ExistenciaArticuloRepository repository;
	
	/**
	 * 
	 */
	public ExistenciaArticuloServiceImpl(ExistenciaArticuloRepository repository) {
		this.repository = repository;
	}

	@Override
	public ExistenciaArticulo persistir(ExistenciaArticulo existenciaArticulo) {
		repository.saveAndFlush(existenciaArticulo);
		return existenciaArticulo;
	}

	@Override
	public Collection<ExistenciaArticulo> listar() {
		return repository.findAll();
	}

	@Override
	public ExistenciaArticulo actualizar(ExistenciaArticulo existenciaArticulo) {
		ExistenciaArticulo existenciaArticuloEnRepo = repository.findById(existenciaArticulo.getIdExistencia()).orElse(null);
		if (Objects.isNull(existenciaArticuloEnRepo))
			throw new NotFoundException("Articulo no se encuentra en inventario.");
		existenciaArticuloEnRepo.setEstado(existenciaArticulo.getEstado());
		return repository.save(existenciaArticuloEnRepo);
	}

	@Override
	public void borrar(ExistenciaArticulo existenciaArticulo) {
		ExistenciaArticulo existenciaArticuloEnRepo = repository.findById(existenciaArticulo.getIdExistencia()).orElse(null);
		if (Objects.isNull(existenciaArticuloEnRepo))
			throw new NotFoundException("Articulo no se encuentra en inventario.");
		existenciaArticuloEnRepo.setEstado(EstadoArticulo.Danado);
		repository.save(existenciaArticuloEnRepo);
	}

	@Override
	public Long contarTotales(int idAlmacen, int idArticulo) {
		return repository.countExistenciaArticuloInAlmacen(idAlmacen, idArticulo);
	}

	@Override
	public ExistenciaArticulo obtener(int idExistenciaArticulo) {
		return repository.findById(idExistenciaArticulo).orElse(null);
	}

}
