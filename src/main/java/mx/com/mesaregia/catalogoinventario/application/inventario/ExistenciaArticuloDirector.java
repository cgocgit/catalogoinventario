package mx.com.mesaregia.catalogoinventario.application.inventario;

import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloService;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public class ExistenciaArticuloDirector {

	private ExistenciaArticuloBuilder builder;
	
	private AlmacenService almacenService;
	
	private ArticuloService articuloService;

	public ExistenciaArticuloDirector(ExistenciaArticuloBuilder builder, AlmacenService almacenService,
			ArticuloService articuloService) {
		super();
		this.builder = builder;
		this.almacenService = almacenService;
		this.articuloService = articuloService;
	}
	
	public void construye(int idArticulo, int idAlmacen, String codigoUnidad) {
		builder.preparar();
		builder.setAlmacen(almacenService.obtenerAlmacen(idAlmacen));
		builder.setCodigoUnidad(codigoUnidad);
		builder.setEstadoArticulo(EstadoArticulo.Disponible);
		builder.setArticulo(articuloService.obtenerArticulo(idArticulo));
	}

}
