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
 * Agrupa productos y servicios por familia o tipo (ej. limpieza, herramientas, servicios t&eacute;cnicos).
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
public class Categoria {

	@Id
	private Integer idCategoria;
	private String nombreCategoria;
	private String descripcion;
	private TipoCategoria categoria;
		
	/**
	 * 
	 */
	public Categoria() {
		/*Main*/
	}

	public Categoria(int idCategoria, String nombreCategoria, String descripcion, TipoCategoria categoria) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}
	
	

}
