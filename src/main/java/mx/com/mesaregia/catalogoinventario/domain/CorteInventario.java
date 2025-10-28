/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa los conteos f&iacute;sicos de inventario para conciliaci&oacute;n
 * con el sistema.
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
public class CorteInventario {

	@Id
	private Integer idCorte;
	private Date fechaCorte;
	private String responsable;
	private String observaciones;
	private TipoCorte tipoCorte;

	/**
	 * 
	 */
	public CorteInventario() {
		/* Constructor principal */
	}

	public CorteInventario(Integer idCorte, Date fechaCorte, String responsable, String observaciones,
			TipoCorte tipoCorte) {
		super();
		this.idCorte = idCorte;
		this.fechaCorte = fechaCorte;
		this.responsable = responsable;
		this.observaciones = observaciones;
		this.tipoCorte = tipoCorte;
	}

}
