/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Indicación del estado f&iacute;sico del Articulo.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum EstadoArticulo {

	DISPONIBLE("Disponible"), RESERVADO("Reservado"), DANADO("Dañado"), MANTENIMIENTO("Mantenimiento"),
	LIMPIEZA("Limpieza");

	private String descripcion;

	/**
	 * 
	 */
	private EstadoArticulo(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
