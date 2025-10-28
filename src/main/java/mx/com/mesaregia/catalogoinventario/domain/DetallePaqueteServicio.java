/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Asocia servicios con un paquete determinado.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Entity
@Getter
@Setter
@ToString
public class DetallePaqueteServicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetallePaqueteServicio;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPaquete", insertable = true, updatable = true)
    private Paquete paquete;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idServicio", insertable = true, updatable = true)
    private Servicio servicio;
    
	private long cantidad;
    
	private double tarifa;
    
	/**
	 * 
	 */
	public DetallePaqueteServicio() {
		/* Constructor principal */
	}

	public DetallePaqueteServicio(Integer idDetallePaqueteServicio, Paquete paquete, Servicio servicio, long cantidad,
			double tarifa) {
		super();
		this.idDetallePaqueteServicio = idDetallePaqueteServicio;
		this.paquete = paquete;
		this.servicio = servicio;
		this.cantidad = cantidad;
		this.tarifa = tarifa;
	}
	
}
