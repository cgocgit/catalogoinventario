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
 * Representa ubicaciones f&iacute;sicas o virtuales de almacenamiento. Permite
 * el control de stock por sitio.
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
public class Almacen {

	@Id
	private Integer idAlmacen;
	private String nombreAlmacen;
	private String ubicacion;
	private String responsable;
	private long capacidad;
	private Estado estado;

	/**
	 * 
	 */
	public Almacen() {
		/* Constructor principal */
	}

	public Almacen(Integer idAlmacen, String nombreAlmacen, String ubicacion, String responsable, long capacidad,
			Estado estado) {
		super();
		this.idAlmacen = idAlmacen;
		this.nombreAlmacen = nombreAlmacen;
		this.ubicacion = ubicacion;
		this.responsable = responsable;
		this.capacidad = capacidad;
		this.estado = estado;
	}

}
