/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

/**
 * Indicac&iacute;n del estado de la Reserva.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public enum EstadoReserva {
	
	PENDIENTE("Pendiente"), CONFIRMADA("Confirmada"), CANCELADA("Cancelada"), FINALIZADA("Finalizada");
	
	private String descripcion;

	private EstadoReserva(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
}
