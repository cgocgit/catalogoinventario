package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Tipo del costo ingreso.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum Tipo {
	COSTO("Costo"), INGRESO("Ingreso");

	private String descripcion;

	private Tipo(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
