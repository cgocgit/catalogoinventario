package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Corte realizado en inventario.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum TipoCorte {
	GENERAL("General"), PARCIAL("Parcial"), PORPRODUCTO("Por Producto");

	private String descripcion;

	private TipoCorte(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
