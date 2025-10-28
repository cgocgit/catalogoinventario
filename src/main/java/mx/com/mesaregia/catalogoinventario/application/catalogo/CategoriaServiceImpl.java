package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.repository.CategoriaRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	/**
	 * 
	 */
	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public Categoria obtenerCategoria(int idCategoria) {
		return categoriaRepository.findById(idCategoria).get();
	}

	@Override
	public Collection<Categoria> obtenerCategorias() {
		return categoriaRepository.findAll();
	}

}
