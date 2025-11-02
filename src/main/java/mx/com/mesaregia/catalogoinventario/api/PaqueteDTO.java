package mx.com.mesaregia.catalogoinventario.api;

import jakarta.validation.constraints.DecimalMin;
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
public class PaqueteDTO {

	@NotNull(message = "Se requiere codigo de paquete.")
	private String codigoPaquete;
	@NotNull(message = "Se requiere descripcion del paquete.")
	private String descripcion;
	@NotNull(message = "Se requiere nombre del paquete.")
	private String nombrePaquete;
	@DecimalMin(value = "0.01")
	private Double precio;
	@NotNull(message = "Se requiere de quien registra.")
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
