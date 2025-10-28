/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Precio de renta versionado en un periodo de tiempo.
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
public class TarifaRenta {

	@Id
	private Integer idTarifa;
	@OneToOne
	@JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
	private Articulo idArticulo;
	private long monto;
	private UnidadTiempo unidadTiempo;
	private Date fechaInicio;
	private Date fechaFin;

	/**
	 * 
	 */
	public TarifaRenta() {
		/* Constructor principal */
	}

	public TarifaRenta(Integer idTarifa, Articulo idArticulo, long monto, UnidadTiempo unidadTiempo, Date fechaInicio,
			Date fechaFin) {
		super();
		this.idTarifa = idTarifa;
		this.idArticulo = idArticulo;
		this.monto = monto;
		this.unidadTiempo = unidadTiempo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

}
