/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Cat&aacute;logo que identifica variantes visuales o de presentación del
 * producto.
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
public class Color {

	@Id
	private Integer idColor;
	private String nombreColor;
	private String codigoHex;
	private String descripcion;
	
	/**
	 * 
	 */
	public Color() {
		/* Constructor principal */
	}

	public Color(Integer idColor, String nombreColor, String codigoHex, String descripcion) {
		super();
		this.idColor = idColor;
		this.nombreColor = nombreColor;
		this.codigoHex = codigoHex;
		this.descripcion = descripcion;
	}

}
