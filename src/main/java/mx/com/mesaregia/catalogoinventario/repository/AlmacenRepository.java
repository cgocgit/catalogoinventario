package mx.com.mesaregia.catalogoinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

}
