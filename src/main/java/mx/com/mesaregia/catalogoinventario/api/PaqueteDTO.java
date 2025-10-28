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
public class PaqueteDTO {

	private String codigoPaquete;
	private String descripcion;
	private String nombrePaquete;
	private Double precio;
	private String usuarioCreacion;
	
	/**
	 * 
	 */
	public PaqueteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PaqueteDTO(String codigoPaquete, String descripcion, String nombrePaquete, Double precio,
			String usuarioCreacion) {
		super();
		this.codigoPaquete = codigoPaquete;
		this.descripcion = descripcion;
		this.nombrePaquete = nombrePaquete;
		this.precio = precio;
		this.usuarioCreacion = usuarioCreacion;
	}

	

}
