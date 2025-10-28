package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
