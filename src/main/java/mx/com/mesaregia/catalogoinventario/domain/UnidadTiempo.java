/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Medida que identifica el tiempo de usao de articulos.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum UnidadTiempo {
	Dia("Dia completo"), Evento("Parcialmente");

	private String descripcion;

	private UnidadTiempo(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
