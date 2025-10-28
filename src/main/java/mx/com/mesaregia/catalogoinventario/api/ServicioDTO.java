package mx.com.mesaregia.catalogoinventario.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Getter
@Setter
@ToString
public class ServicioDTO {

	private String codigoServicio;
	private String nombreServicio;
	private String descripcion;
	private TipoServicio tipoServicio;
	private Double costo;
	private Double tarifaBase;
	private Integer idCategoria;

	/**
	 * 
	 */
	public ServicioDTO() {
		// TODO Auto-generated constructor stub
	}

	public ServicioDTO(String codigoServicio, String nombreServicio, String descripcion, TipoServicio tipoServicio,
			Double costo, Double tarifaBase, Integer idCategoria) {
		super();
		this.codigoServicio = codigoServicio;
		this.nombreServicio = nombreServicio;
		this.descripcion = descripcion;
		this.tipoServicio = tipoServicio;
		this.costo = costo;
		this.tarifaBase = tarifaBase;
		this.idCategoria = idCategoria;
	}

}
