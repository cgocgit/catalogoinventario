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
 * Bloqueo temporal de artículos para un evento espec&iacute;fico,
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
public class ReservaInventario {

	@Id
	private Integer idReserva;
	@OneToOne
	@JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
	private Articulo articulo;
	private int idEvento;
	private Date fechaInicio;
	private Date fechaFin;
	private long cantidad;
	private EstadoReserva estado;
	private Date fechaRegistro;

	public ReservaInventario() {
		/* Constructor principal */
	}

	public ReservaInventario(Integer idReserva, Articulo articulo, int idEvento, Date fechaInicio, Date fechaFin,
			long cantidad, EstadoReserva estado, Date fechaRegistro) {
		super();
		this.idReserva = idReserva;
		this.articulo = articulo;
		this.idEvento = idEvento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}

}
