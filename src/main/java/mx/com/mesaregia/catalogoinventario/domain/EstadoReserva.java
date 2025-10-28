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
	
	Pendiente("Pendiente"), Confirmada("Confirmada"), Cancelada("Cancelada"), Finalizada("Finalizada");
	
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
