package mx.com.mesaregia.catalogoinventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Transferencia de datos para entidad Articulo.
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
	
	@NotNull(message = "Se requiere la descripción de las medidas.")
    private String medidas;
	@NotNull(message = "Se requiere descripción del material con el que esta elaborado.")
    private String material;
	@NotNull(message = "Se requiere descripción de sus capacidades.")
    private String capacidad;
    private String detalles;
    
	/**
	 * 
	 */
	public ArticuloDTO() {
		/* Constructor principal */
	}

	public ArticuloDTO(@NotNull(message = "Se requiere el nombre del articulo.") String nombreArticulo,
			@NotNull(message = "Se requiere descripion del articulo.") String descripcionArticulo,
			@NotNull(message = "Se requiere usario de quien registra.") String usuarioRegistra,
			@NotNull(message = "Se requiere la unidad de medida.") String unidadMedida,
			@Min(value = 1, message = "El identificador no debe ser menor a Uno.") int idCategoria,
			@Min(value = 1, message = "El identificador no debe ser menor a Uno.") int idColor,
			@Min(value = 1, message = "El identificador no debe ser menor a Uno.") int idTipoArticulo,
			@NotNull(message = "Se requiere la descripción de las medidas.") String medidas,
			@NotNull(message = "Se requiere descripción del material con el que esta elaborado.") String material,
			@NotNull(message = "Se requiere descripción de sus capacidades.") String capacidad, String detalles) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.descripcionArticulo = descripcionArticulo;
		this.usuarioRegistra = usuarioRegistra;
		this.unidadMedida = unidadMedida;
		this.idCategoria = idCategoria;
		this.idColor = idColor;
		this.idTipoArticulo = idTipoArticulo;
		this.medidas = medidas;
		this.material = material;
		this.capacidad = capacidad;
		this.detalles = detalles;
	}
	
}
