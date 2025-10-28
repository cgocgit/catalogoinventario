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
public class GenericResponse {
	
	private String codigo;
	private String mensaje;
	
	private Object obj;

	/**
	 * 
	 */
	public GenericResponse() {
		// TODO Auto-generated constructor stub
	}

	public GenericResponse(String codigo, String mensaje, Object obj) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.obj = obj;
	}

	
}
