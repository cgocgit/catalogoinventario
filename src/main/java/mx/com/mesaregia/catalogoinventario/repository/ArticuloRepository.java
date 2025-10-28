package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

}
