package mx.com.mesaregia.catalogoinventario.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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

	@NotNull(message = "Se requiere del codigo de servicio.")
	private String codigoServicio;
	@NotNull(message = "Se requiere el nombre de servicio.")
	private String nombreServicio;
	@NotNull(message = "Se requiere la descripcion de servicio.")
	private String descripcion;
	@NotNull(message = "Se requiere el tipo de servicio.")
	private TipoServicio tipoServicio;
	@DecimalMin(value = "0.01", message = "Se requiere un costo valido.")
	private Double costo;
	@DecimalMin(value = "0.01", message = "Se requiere una tarifa valida.")
	private Double tarifaBase;
	@NotNull(message = "Se requiere la categoria del servicio.")
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
