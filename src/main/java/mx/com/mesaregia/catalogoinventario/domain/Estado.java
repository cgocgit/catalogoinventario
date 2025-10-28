package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Indicaci&oacute;n del Estado del elemento.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum Estado {
	Activo ("Activo"), Inactivo ("Inactivo");
	
	private String descripcion;
	
	private Estado(String descripcion) { this.descripcion = descripcion;}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
}
