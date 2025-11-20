/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "Indique el codigo del servicio.")
	private String codigoServicio;
	@NotNull(message = "Indique nombre del servicio.")
	private String nombreServicio;
	private String descripcion;
	@NotNull(message = "Tipo de Servicio is Null")
	private TipoServicio tipoServicio;
	private Double costo;
	private boolean activo;
	private Double tarifaBase;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategoria", insertable = true, updatable = true)
	private Categoria categoria;
	
	@NotNull(message = "Fecha en que es registrado.")
	private Date fechaRegistro;
	@NotNull(message = "Debe indicar quien genera el registro.")
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;

	/**
	 * 
	 */
	public Servicio() {
		/* Constructor principal */
	}

	public Servicio(Integer idServicio, @NotNull(message = "Indique el codigo del servicio.") String codigoServicio,
			@NotNull(message = "Indique nombre del servicio.") String nombreServicio, String descripcion,
			@NotNull(message = "Tipo de Servicio is Null") TipoServicio tipoServicio, Double costo, boolean activo,
			Double tarifaBase, Categoria categoria,
			@NotNull(message = "Fecha en que es registrado.") Date fechaRegistro,
			@NotNull(message = "Debe indicar quien genera el registro.") String creadoPor, Date fechaModificacion,
			String modificadoPor) {
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
		this.fechaRegistro = fechaRegistro;
		this.creadoPor = creadoPor;
		this.fechaModificacion = fechaModificacion;
		this.modificadoPor = modificadoPor;
	}

	
}
