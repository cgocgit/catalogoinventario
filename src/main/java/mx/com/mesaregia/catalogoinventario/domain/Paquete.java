package mx.com.mesaregia.catalogoinventario.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
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
public class Paquete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaquete;
	private String nombrePaquete;
	private String descripcion;
	private Double precio;
	private boolean activo;
	private String codigoPaquete;
	private Date fechaRegistro;
	private String usuarioCreacion;

	public Paquete() {
		/* Constructor principal */
	}

	public Paquete(Integer idPaquete, String nombrePaquete, String descripcion, Double precio, boolean activo,
			String codigoPaquete, Date fechaRegistro, String usuarioCreacion) {
		super();
		this.idPaquete = idPaquete;
		this.nombrePaquete = nombrePaquete;
		this.descripcion = descripcion;
		this.precio = precio;
		this.activo = activo;
		this.codigoPaquete = codigoPaquete;
		this.fechaRegistro = fechaRegistro;
		this.usuarioCreacion = usuarioCreacion;
	}

}
