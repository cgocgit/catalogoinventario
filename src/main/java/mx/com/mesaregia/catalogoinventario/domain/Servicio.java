/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Describe el elemento intangible ofrecido (buffet, decoración, transporte,
 * montaje).
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
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicio;
	private String codigoServicio;
	private String nombreServicio;
	private String descripcion;
	private TipoServicio tipoServicio;
	private Double costo;
	private boolean activo;
	private Double tarifaBase;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategoria", insertable = true, updatable = true)
	private Categoria categoria;

	/**
	 * 
	 */
	public Servicio() {
		/* Constructor principal */
	}

	public Servicio(Integer idServicio, String codigoServicio, String nombreServicio, String descripcion,
			TipoServicio tipoServicio, Double costo, boolean activo, Double tarifaBase, Categoria categoria) {
		super();
		this.idServicio = idServicio;
		this.codigoServicio = codigoServicio;
		this.nombreServicio = nombreServicio;
		this.descripcion = descripcion;
		this.tipoServicio = tipoServicio;
		this.costo = costo;
		this.activo = activo;
		this.tarifaBase = tarifaBase;
		this.categoria = categoria;
	}

}
