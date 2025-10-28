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
 * Detalla los costos unitarios de adquisiciones o ingresos de producto.
 * &Uacute;til para control contable.
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
public class CostoIngresos {

	@Id
	private Integer idCostoIngreso;
	@OneToOne
	@JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
	private Articulo articulo;
	private Tipo tipo;
	private long monto;
	private Date fechaRegistro;
	private String documentoReferencia;
	private TipoMovimiento tipoMovimiento;

	/**
	 * 
	 */
	public CostoIngresos() {
		/* Constructor principal */
	}

	public CostoIngresos(Integer idCostoIngreso, Articulo articulo, Tipo tipo, long monto, Date fechaRegistro,
			String documentoReferencia, TipoMovimiento tipoMovimiento) {
		super();
		this.idCostoIngreso = idCostoIngreso;
		this.articulo = articulo;
		this.tipo = tipo;
		this.monto = monto;
		this.fechaRegistro = fechaRegistro;
		this.documentoReferencia = documentoReferencia;
		this.tipoMovimiento = tipoMovimiento;
	}

}
