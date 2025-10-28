package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.mesaregia.catalogoinventario.api.ItsExistException;
import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.domain.MovimientoInventario;
import mx.com.mesaregia.catalogoinventario.domain.TipoMovimiento;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public abstract class InventarioOperativoTemplate implements InventarioService {

	private static final Logger log = LoggerFactory.getLogger(InventarioOperativoTemplate.class);
	/**
	 * 
	 */
	protected InventarioOperativoTemplate() {
		/* Constructor principal */
	}

	@Override
	public ExistenciaArticulo agregarArticulo(int idAlmacen, int idArticulo, String codigoUnidad)
			throws NotFoundException, ItsExistException {
		log.info("Inicia proceso de registro de articulo en inventario");
		
		if (existeCodigo(codigoUnidad))
			throw new ItsExistException("Codigo de unidad existente.");

		if (!existeArticulo(idArticulo))
			throw new NotFoundException("El articulo no se encuentra registrado.");

		if (!existeAlmace(idAlmacen))
			throw new NotFoundException("El almacen no existe.");

		log.info("Validaciones exitosas.");
		ExistenciaArticulo existenciaArticuloPersistir = construirExistenciaArticulo(idAlmacen, idArticulo,
				codigoUnidad);

		agregarArticulo(existenciaArticuloPersistir);
		log.info("Articulo en inventario.");

		actualizarInventario(existenciaArticuloPersistir.getAlmacen().getIdAlmacen(),
				existenciaArticuloPersistir.getArticulo().getIdArticulo().intValue());

		MovimientoInventario movimientoInventario = construirMovimientoInventario(existenciaArticuloPersistir, "Alta de articulo en inventario", TipoMovimiento.Entrada);
		registrarMovimiento(movimientoInventario);

		return existenciaArticuloPersistir;
	}

	@Override
	public void actualizarEstadoArticulo(int idExistenciaArticulo, EstadoArticulo estado) throws NotFoundException {

		ExistenciaArticulo existencia = busercarArticulo(idExistenciaArticulo);
		if (Objects.isNull(existencia))
			throw new NotFoundException("El articulo no se tiene en el inventario.");

		existencia.setEstado(estado);

		actualizarArticulo(existencia);
	}

	/**
	 * @param existenciaArticuloPersistir
	 * @return
	 */
	protected abstract MovimientoInventario construirMovimientoInventario(
			ExistenciaArticulo existenciaArticuloPersistir, String observaciones, TipoMovimiento tipo);

	/**
	 * @param idAlmacen
	 * @param idArticulo
	 * @param codigoUnidad
	 * @return
	 */
	protected abstract ExistenciaArticulo construirExistenciaArticulo(int idAlmacen, int idArticulo,
			String codigoUnidad);

	/**
	 * @param idAlmacen
	 * @return
	 */
	protected abstract boolean existeAlmace(int idAlmacen);

	/**
	 * @param idArticulo
	 * @return
	 */
	protected abstract boolean existeArticulo(int idArticulo);

	/**
	 * @param codigoUnidad
	 * @return
	 */
	protected abstract boolean existeCodigo(String codigoUnidad);

	/**
	 * 
	 * @param existenciaArticulo
	 */
	protected abstract void agregarArticulo(ExistenciaArticulo existenciaArticulo);

	/**
	 * 
	 * @param idInventario
	 * @param idArticulo
	 */
	protected abstract void actualizarInventario(int idInventario, int idArticulo);

	/**
	 * 
	 * @param movimiento
	 */
	protected abstract void registrarMovimiento(MovimientoInventario movimiento);

	/**
	 * 
	 * @param existenciaArticulo
	 */
	protected abstract void actualizarArticulo(ExistenciaArticulo existenciaArticulo);

	/**
	 * 
	 * @param idExistenciaArticulo
	 * @return
	 */
	protected abstract ExistenciaArticulo busercarArticulo(int idExistenciaArticulo);

}
