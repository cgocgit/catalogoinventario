/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Inventario del articulo (codigo de unidad) y su estado, en algun almacen.
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
public class ExistenciaArticulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idExistencia;
	
	@ManyToOne
	@JoinColumn(name = "idArticulo", insertable = true, updatable = true)
	private Articulo articulo;
	
	private String codigoUnidad;
	
	private EstadoArticulo estado;
	
	@ManyToOne
	@JoinColumn(name = "idAlmacen", insertable = true, updatable = true)
	private Almacen almacen;

	/**
	 * 
	 */
	public ExistenciaArticulo() {
		/* Constructor principal */
	}

	public ExistenciaArticulo(Integer idExistencia, Articulo articulo, String codigoUnidad, EstadoArticulo estado,
			Almacen almacen) {
		super();
		this.idExistencia = idExistencia;
		this.articulo = articulo;
		this.codigoUnidad = codigoUnidad;
		this.estado = estado;
		this.almacen = almacen;
	}

}
