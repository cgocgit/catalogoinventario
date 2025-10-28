package mx.com.mesaregia.catalogoinventario.application.inventario;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloService;
import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.domain.Inventario;
import mx.com.mesaregia.catalogoinventario.domain.MovimientoInventario;
import mx.com.mesaregia.catalogoinventario.domain.TipoMovimiento;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Component
public class InventarioServiceImpl extends InventarioOperativoTemplate implements InventarioService {

	private final ExistenciaArticuloService existenciaArticuloService;

	private final ArticuloService articuloService;

	private final AlmacenService almacenService;

	private final InventariadoService inventariadoService;
	
	private final MovimientoInventarioService movimientoInventarioService;

	/**
	 * 
	 */
	public InventarioServiceImpl(ExistenciaArticuloService existenciaArticuloService, ArticuloService articuloService,
			AlmacenService almacenService, InventariadoService inventariadoService, MovimientoInventarioService movimiInventarioService) {
		this.existenciaArticuloService = existenciaArticuloService;
		this.articuloService = articuloService;
		this.almacenService = almacenService;
		this.inventariadoService = inventariadoService;
		this.movimientoInventarioService = movimiInventarioService;
	}

	@Override
	public Collection<Inventario> consultarInventario() {
		return inventariadoService.obtener();
	}

	@Override
	protected MovimientoInventario construirMovimientoInventario(ExistenciaArticulo existenciaArticuloPersistir,
			String observaciones, TipoMovimiento tipo) {
		Inventario inventario = inventariadoService.obtener(existenciaArticuloPersistir.getAlmacen().getIdAlmacen(),
				existenciaArticuloPersistir.getArticulo().getIdArticulo().intValue());
		Date registro = new Date();
		MovimientoInventario movimiento = new MovimientoInventario();
		movimiento.setCantidad(1L);
		movimiento.setFechaMovimiento(registro);
		movimiento.setIdInventario(inventario);
		movimiento.setObservaciones(observaciones);
		movimiento.setReferenciaDocumento(null);
		movimiento.setTipoMovimiento(tipo);
		movimiento.setUsuarioResponsable("Administrador");
		return movimiento;
	}

	@Override
	protected ExistenciaArticulo construirExistenciaArticulo(int idAlmacen, int idArticulo, String codigoUnidad) {
		ExistenciaArticuloBuilder builder = new ExistenciaArticuloBuilderImpl();
		ExistenciaArticuloDirector director = new ExistenciaArticuloDirector(builder, almacenService, articuloService);
		director.construye(idArticulo, idAlmacen, codigoUnidad);
		return builder.get();
	}

	@Override
	protected boolean existeAlmace(int idAlmacen) {
		Almacen almacen = almacenService.obtenerAlmacen(idAlmacen);
		return Objects.nonNull(almacen);
	}

	@Override
	protected boolean existeArticulo(int idArticulo) {
		return Objects.nonNull(articuloService.obtenerArticulo(idArticulo));
	}

	@Override
	protected boolean existeCodigo(String codigoUnidad) {
		ExistenciaArticulo existenciaArticulo = existenciaArticuloService.listar().stream()
				.filter(e -> e.getCodigoUnidad().equals(codigoUnidad)).findFirst().orElse(null);
		return Objects.nonNull(existenciaArticulo);
	}

	@Override
	protected void agregarArticulo(ExistenciaArticulo existenciaArticulo) {
		existenciaArticuloService.persistir(existenciaArticulo);
	}

	@Override
	protected void actualizarInventario(int idAlmacen, int idArticulo) {
		Long cantidadActual = existenciaArticuloService.contarTotales(idAlmacen, idArticulo);
		Inventario inventario = inventariadoService.obtener(idAlmacen, idArticulo);
		if (Objects.isNull(inventario)) {
			inventario = new Inventario();
			Almacen almacen = almacenService.obtenerAlmacen(idAlmacen);
			inventario.setAlmacen(almacen);
			Articulo articulo = articuloService.obtenerArticulo(idArticulo);
			inventario.setArticulo(articulo);
			inventario.setStockMinimo(1);
			inventario.setStockMaximo(1);
			inventario.setFechaActualizacion(new Date());
			inventario.setUsuarioActualizacion("Admin");
			inventariadoService.registrar(inventario);
		} else {
			inventario.setCantidadActual(cantidadActual);
			inventariadoService.actualizar(inventario);
		}
		
	}

	@Override
	protected void registrarMovimiento(MovimientoInventario movimiento) {
		movimientoInventarioService.registraMovimiento(movimiento);
	}

	@Override
	protected void actualizarArticulo(ExistenciaArticulo existenciaArticulo) {
		existenciaArticuloService.actualizar(existenciaArticulo);

	}

	@Override
	protected ExistenciaArticulo busercarArticulo(int idExistenciaArticulo) {
		return existenciaArticuloService.obtener(idExistenciaArticulo);
	}

}
