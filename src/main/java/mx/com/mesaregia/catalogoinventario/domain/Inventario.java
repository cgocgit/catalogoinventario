/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Inventario global del articulo en almacen.
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
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInventario;
	@ManyToOne
	@JoinColumn(name = "idArticulo", insertable = true, updatable = true)
	private Articulo articulo;
	@ManyToOne
	@JoinColumn(name = "idAlmacen",  insertable = true, updatable = true)
	private Almacen almacen;
	private long cantidadActual;
	private long stockMinimo;
	private long stockMaximo;
	private Date fechaActualizacion;
	private String usuarioActualizacion;

	/**
	 * 
	 */
	public Inventario() {
		/* Constructor principal */
	}

	public Inventario(Integer idInventario, Articulo articulo, Almacen almacen, long cantidadActual, long stockMinimo,
			long stockMaximo, Date fechaActualizacion, String usuarioActualizacion) {
		super();
		this.idInventario = idInventario;
		this.articulo = articulo;
		this.almacen = almacen;
		this.cantidadActual = cantidadActual;
		this.stockMinimo = stockMinimo;
		this.stockMaximo = stockMaximo;
		this.fechaActualizacion = fechaActualizacion;
		this.usuarioActualizacion = usuarioActualizacion;
	}

}
