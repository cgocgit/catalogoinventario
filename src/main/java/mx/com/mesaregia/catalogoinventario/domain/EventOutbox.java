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
 * Registro de cambios relevantes y propagaci&oacute;n de eventos.
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
public class EventOutbox {

	@Id
	private Integer idEvento;
	private String tipoEvento;
	private String JSON;
	private Date fechaRegistro;
	private boolean procesado;

	/**
	 * 
	 */
	public EventOutbox() {
		/* Constructor principal */
	}

	public EventOutbox(Integer idEvento, String tipoEvento, String jSON, Date fechaRegistro, boolean procesado) {
		super();
		this.idEvento = idEvento;
		this.tipoEvento = tipoEvento;
		JSON = jSON;
		this.fechaRegistro = fechaRegistro;
		this.procesado = procesado;
	}

}
