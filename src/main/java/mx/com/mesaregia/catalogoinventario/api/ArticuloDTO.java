package mx.com.mesaregia.catalogoinventario.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Getter
@Setter
@ToString
public class ArticuloDTO {

	private Long idArticulo;
	
	private String nombreArticulo;
	private String descripcionArticulo;
	private String usuarioRegistra;
	private String unidadMedida;
	private int idCategoria;
	private int idColor;
	private int idTipoArticulo;
	
	/**
	 * 
	 */
	public ArticuloDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArticuloDTO(String nombreArticulo, String descripcionArticulo, String usuarioRegistra, String unidadMedida,
			int idCategoria, int idColor, int idTipoArticulo) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.descripcionArticulo = descripcionArticulo;
		this.usuarioRegistra = usuarioRegistra;
		this.unidadMedida = unidadMedida;
		this.idCategoria = idCategoria;
		this.idColor = idColor;
		this.idTipoArticulo = idTipoArticulo;
	}
	
}
