package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;
/**
 * JPA for entity Categoria.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
