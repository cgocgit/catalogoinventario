/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Detalle de conteos f&iacute;sicos por producto durante un corte de
 * inventario.
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
public class DetalleCorteInventario {

	@Id
	private Integer idDetalleCorte;
	@OneToOne
	@JoinColumn(name = "idCorte", referencedColumnName = "idCorte")
	private CorteInventario corteInventario;
	@OneToOne
	@JoinColumn(name = "idInventario", referencedColumnName = "idInventario")
	private Inventario inventario;
	private long cantidadFisica;
	private long cantidadSistema;
	private long diferencia;
	private String observacion;

	/**
	 * 
	 */
	public DetalleCorteInventario() {
		/* Constructor principal */
	}

	public DetalleCorteInventario(Integer idDetalleCorte, CorteInventario corteInventario, Inventario inventario,
			long cantidadFisica, long cantidadSistema, long diferencia, String observacion) {
		super();
		this.idDetalleCorte = idDetalleCorte;
		this.corteInventario = corteInventario;
		this.inventario = inventario;
		this.cantidadFisica = cantidadFisica;
		this.cantidadSistema = cantidadSistema;
		this.diferencia = diferencia;
		this.observacion = observacion;
	}

}
