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
 * Asocia productos con un paquete espec&iacute;fico.
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
public class DetallePaqueteArticulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetallePaqueteProducto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPaquete", insertable = true, updatable = true)
	private Paquete paquete;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idArticulo", insertable = true, updatable = true)
	private Articulo articulo;
	
	private long cantidad;
	
	private double precioUnitario;

	public DetallePaqueteArticulo() {
		/* Constructor principal */
	}

	public DetallePaqueteArticulo(Integer idDetallePaqueteProducto, Paquete paquete, Articulo articulo, long cantidad,
			double precioUnitario) {
		super();
		this.idDetallePaqueteProducto = idDetallePaqueteProducto;
		this.paquete = paquete;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

}
