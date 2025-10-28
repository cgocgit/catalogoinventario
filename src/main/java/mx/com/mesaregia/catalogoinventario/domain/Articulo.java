package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

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
 * Contiene la informaci&oacute;n principal de cada art&iacute;culo o material
 * gestionado en el sistema. Permite su clasificaci&oacute;n, valoraci&oacute;n
 * y control de inventario.
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
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArticulo;
	private String nombreArticulo;
	private String descripcionArticulo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoArticulo", insertable = true, updatable = true)
	private TipoArticulo tipoArticulo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategoria", insertable = true, updatable = true)
	private Categoria categoria;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idColor", insertable = true, updatable = true)
	private Color color;
	private String unidadMedida;
	private boolean activo;
	private Date fechaRegistro;
	private String usuarioRegistro;

	public Articulo() {
		/* Main constructor */
	}

	public Articulo(Long idArticulo, String nombreArticulo, String descripcionArticulo, TipoArticulo tipoArticulo,
			Categoria categoria, Color color, String unidadMedida, boolean activo, Date fechaRegistro,
			String usuarioRegistro) {
		super();
		this.idArticulo = idArticulo;
		this.nombreArticulo = nombreArticulo;
		this.descripcionArticulo = descripcionArticulo;
		this.tipoArticulo = tipoArticulo;
		this.categoria = categoria;
		this.color = color;
		this.unidadMedida = unidadMedida;
		this.activo = activo;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}

}
