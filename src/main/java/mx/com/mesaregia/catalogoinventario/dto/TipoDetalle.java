package mx.com.mesaregia.catalogoinventario.dto;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public enum TipoDetalle {

	Articulo("Articulo"), Servicio("Servicio");
	
	private String descripcion;
	
	/**
	 * 
	 */
	TipoDetalle(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
