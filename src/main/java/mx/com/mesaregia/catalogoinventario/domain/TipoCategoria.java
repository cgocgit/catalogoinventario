/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Clasificaci&oacute;n del elemento ofertado.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum TipoCategoria {
	Producto ("Producto"), Servicio("Servicio");
	
	private String descripcion;

	private TipoCategoria(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
