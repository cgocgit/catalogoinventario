package mx.com.mesaregia.catalogoinventario.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

	@NotNull(message = "Se requiere el nombre del articulo.")
	private String nombreArticulo;
	@NotNull(message = "Se requiere descripion del articulo.")
	private String descripcionArticulo;
	@NotNull(message = "Se requiere usario de quien registra.")
	private String usuarioRegistra;
	@NotNull(message = "Se requiere la unidad de medida.")
	private String unidadMedida;
	@Min(value = 1, message = "El identificador no debe ser menor a Uno.")
	private int idCategoria;
	@Min(value = 1, message = "El identificador no debe ser menor a Uno.")
	private int idColor;
	@Min(value = 1, message = "El identificador no debe ser menor a Uno.")
	private int idTipoArticulo;
	
	/**
	 * 
	 */
	public ArticuloDTO() {
		/* Constructor principal */
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
