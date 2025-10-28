/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Enumera los tipos de movimientos realizados en inventario y 
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum TipoMovimiento {
	Entrada("Entrada"), Salida("Salida"), Ajuste("Ajuste"), Compra("Compra"), Devolucion("Devolución");

	private String descripcion;

	private TipoMovimiento(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
