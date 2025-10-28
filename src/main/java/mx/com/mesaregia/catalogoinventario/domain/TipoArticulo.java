package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Define la naturaleza o uso del producto (materia prima, terminado,
 * consumible). Facilita reportes y pol&iacute;ticas de inventario.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * @version 1.0.0
 *
 **/
@Entity
@Getter
@Setter
@ToString
public class TipoArticulo {

	@Id
	private int idTipoArticulo;
	private String nombreTipo;
	private String descripcion;
	private NivelControl nivelControl;
	
	public TipoArticulo() {
		/* Constructor principal */
	}

	public TipoArticulo(int idTipoArticulo, String nombreTipo, String descripcion, NivelControl nivelControl,
			Set<Articulo> articulos) {
		super();
		this.idTipoArticulo = idTipoArticulo;
		this.nombreTipo = nombreTipo;
		this.descripcion = descripcion;
		this.nivelControl = nivelControl;	
	}

}
