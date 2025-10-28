/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Clasifica el servicio en un tipo, siendo todos los tipos de serrvicio ofertados.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum TipoServicio {

	Banquete("Banquete"), Decoracion("Decoración"), Flete("Flete"), Meseros("Meseros"), Otro("Otro");

	private String descripcion;

	private TipoServicio(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
