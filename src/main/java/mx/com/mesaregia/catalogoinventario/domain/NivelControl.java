/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Nivel de control en el inventario de un articulo.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum NivelControl {
	Bajo("Bajo"), Medio("Medio"), Alto("Alto");

	private String descipcion;

	private NivelControl(String descipcion) {
		this.descipcion = descipcion;
	}

	/**
	 * @return the descipcion
	 */
	public String getDescipcion() {
		return descipcion;
	}
}
