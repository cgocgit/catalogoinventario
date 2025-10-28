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
public class RegistroInventarioDTO {

	private int idInventario;
	private int idArticulo;
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
