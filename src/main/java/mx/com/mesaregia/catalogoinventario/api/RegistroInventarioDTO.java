package mx.com.mesaregia.catalogoinventario.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Data
public class RegistroInventarioDTO {

	@Min(value = 1, message = "Identificador de inventario requerido.")
	private int idInventario;
	@Min(value = 1, message = "Identificador de articulo requerido.")
	private int idArticulo;
	@NotNull(message = "Codigo de unidad requerido.")
	private String codigoUnidad;
	
	/**
	 * 
	 */
	public RegistroInventarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegistroInventarioDTO(int idInventario, int idArticulo, String codigoUnidad) {
		super();
		this.idInventario = idInventario;
		this.idArticulo = idArticulo;
		this.codigoUnidad = codigoUnidad;
	}

	
}
