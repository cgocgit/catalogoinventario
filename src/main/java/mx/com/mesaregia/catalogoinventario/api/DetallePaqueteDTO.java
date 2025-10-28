package mx.com.mesaregia.catalogoinventario.api;

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

	private int idPaquete;
	private int idArticuloServicio;
	private int cantidad;
	private double precio;
	
	/**
	 * 
	 */
	public DetallePaqueteDTO() {
		/* Constructor principal*/
	}

}
