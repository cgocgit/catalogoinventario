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

	BANQUETE("Banquete"), DECORACION("Decoración"), FLETE("Flete"), MESEROS("Meseros"), OTRO("Otro");

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
