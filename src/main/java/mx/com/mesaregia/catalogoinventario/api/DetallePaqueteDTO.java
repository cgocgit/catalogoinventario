package mx.com.mesaregia.catalogoinventario.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Data
public class DetallePaqueteDTO {

	@Min(value = 1, message = "Se requiere un identificador valido.")
	private int idPaquete;
	@Min(value = 1, message = "Se requiere un identificador valido.")
	private int idArticuloServicio;
	@Min(value = 1, message = "Se requiere un cantidad valida.")
	private int cantidad;
	@DecimalMin(value = "0.01", message = "Se requiere un precio valido.")
	private double precio;
	
	/**
	 * 
	 */
	public DetallePaqueteDTO() {
		/* Constructor principal*/
	}

}
