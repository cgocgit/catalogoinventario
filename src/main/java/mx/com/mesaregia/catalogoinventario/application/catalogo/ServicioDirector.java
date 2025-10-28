package mx.com.mesaregia.catalogoinventario.application.catalogo;

import org.springframework.stereotype.Component;

import lombok.Setter;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Component
public class ServicioDirector {

	private final CategoriaService categoriaService;

	@Setter
	private Integer idServicio;
	@Setter
	private String codigoServicio;
	@Setter
	private String nombreServicio;
	@Setter
	private String descripcion;
	@Setter
	private TipoServicio tipoServicio;
	@Setter
	private Double costo;
	@Setter
	private Double tarifaBase;
	@Setter
	private Integer idCategoria;
	@Setter
	private ServicioBuilder builder;

	/**
	 * 
	 */
	private ServicioDirector(CategoriaService categoriaService, ColorService colorService,
			TipoArticuloService tipoArticuloService) {
		/* Constructor principal */
		this.categoriaService = categoriaService;
	}

	public void construirServicio() {

		builder.preparar();

		builder.setIdServicio(idServicio);
		builder.setCodigoServicio(codigoServicio);
		builder.setCategoria(obtenerCategoria(idCategoria));

		builder.setDescripcion(descripcion);
		builder.setNombreServicio(nombreServicio);
		builder.setTipoServicio(tipoServicio);
		builder.setCosto(costo);
		builder.setTarifaBase(tarifaBase);

	}

	/**
	 * @param idCategoria2
	 * @return
	 */
	private Categoria obtenerCategoria(int idCategoria) {
		return categoriaService.obtenerCategoria(idCategoria);
	}

	public Servicio obtenerServicio() {
		return builder.get();
	}

}
