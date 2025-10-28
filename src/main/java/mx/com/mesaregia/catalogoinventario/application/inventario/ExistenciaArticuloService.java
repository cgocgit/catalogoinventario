package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;

import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public interface ExistenciaArticuloService {

	ExistenciaArticulo persistir(ExistenciaArticulo existenciaArticulo);
	
	Collection<ExistenciaArticulo> listar();
	
	ExistenciaArticulo actualizar(ExistenciaArticulo existenciaArticulo);
	
	void borrar(ExistenciaArticulo existenciaArticulo);

	/**
	 * @param idAlmacen
	 * @param idArticulo
	 * @return
	 */
	Long contarTotales(int idAlmacen, int idArticulo);

	/**
	 * @param idExistenciaArticulo
	 * @return
	 */
	ExistenciaArticulo obtener(int idExistenciaArticulo);
}
