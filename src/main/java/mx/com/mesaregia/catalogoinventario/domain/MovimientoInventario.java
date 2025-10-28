package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Registra cada operaci&oacute;n que afecta el inventario (entradas, salidas, ajustes).
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
public class MovimientoInventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;
	@OneToOne
	@JoinColumn(name = "idInventario", referencedColumnName = "idInventario")
    private Inventario idInventario;
    private TipoMovimiento tipoMovimiento;
    private Long cantidad;
    private Date fechaMovimiento;
    private String observaciones;
    private String referenciaDocumento;
    private String usuarioResponsable;

	public MovimientoInventario() {
		/* Constructor principal */
	}

	public MovimientoInventario(Integer idMovimiento, Inventario idInventario, TipoMovimiento tipoMovimiento,
			Long cantidad, Date fechaMovimiento, String observaciones, String referenciaDocumento,
			String usuarioResponsable) {
		super();
		this.idMovimiento = idMovimiento;
		this.idInventario = idInventario;
		this.tipoMovimiento = tipoMovimiento;
		this.cantidad = cantidad;
		this.fechaMovimiento = fechaMovimiento;
		this.observaciones = observaciones;
		this.referenciaDocumento = referenciaDocumento;
		this.usuarioResponsable = usuarioResponsable;
	}
	
}
