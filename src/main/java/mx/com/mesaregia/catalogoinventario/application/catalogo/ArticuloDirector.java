package mx.com.mesaregia.catalogoinventario.application.catalogo;

import org.springframework.stereotype.Component;

import lombok.Setter;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class ArticuloDirector {
	
	private final CategoriaService categoriaService;
	private final ColorService colorService;
	private final TipoArticuloService tipoArticuloService;
	
	@Setter
	private Long idArticulo;
	@Setter
	private ArticuloBuilder builder;
	@Setter
	private String nombreArticulo;
	@Setter
	private String descripcionArticulo;
	@Setter
	private String usuarioRegistra;
	@Setter
	private String unidadMedida;
	@Setter
	private int idCategoria;
	@Setter
	private int idColor;
	@Setter
	private int idTipoArticulo;
	
	/**
	 * 
	 */
	private ArticuloDirector(CategoriaService categoriaService, ColorService colorService, TipoArticuloService tipoArticuloService) {
		/* Constructor principal */
		this.categoriaService = categoriaService;
		this.colorService = colorService;
		this.tipoArticuloService = tipoArticuloService;
	}

	
	public void construirArticulo() {
	
		builder.preparar();
		
		builder.setIdArticulo(idArticulo);
		
		builder.setCategoria(obtenerCategoria(idCategoria));
		builder.setColor(obtenerColor(idColor));
		builder.setTipo(obtenerTipoArticulo(idTipoArticulo));
		
		builder.setDescripcion(descripcionArticulo);
		builder.setFechaRegistro();
		builder.setNombre(nombreArticulo);
		builder.setUnidadMedida(unidadMedida);
		builder.setUsuario(usuarioRegistra);
	}
	
	/**
	 * @param idTipoArticulo2
	 * @return
	 */
	private TipoArticulo obtenerTipoArticulo(int idTipoArticulo) {
		return tipoArticuloService.obtenerTipoArticulo(idTipoArticulo);
	}


	/**
	 * @param idColor2
	 * @return
	 */
	private Color obtenerColor(int idColor) {
		return colorService.obtenerColor(idColor);
	}


	/**
	 * @param idCategoria2
	 * @return
	 */
	private Categoria obtenerCategoria(int idCategoria) {
		return categoriaService.obtenerCategoria(idCategoria);
	}


	public Articulo obtenerArticulo() {
		return builder.get();
	}

}
