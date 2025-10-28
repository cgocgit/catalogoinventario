package mx.com.mesaregia.catalogoinventario.api;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public class CommonsController {

	/**
	 * 
	 */
	public CommonsController() {
		super();
	}

	/**
	 * @return
	 */
	protected GenericResponse getExito(String codigo, String mensaje, Object obj) {
		return new GenericResponse(codigo, mensaje, obj);
	}

}