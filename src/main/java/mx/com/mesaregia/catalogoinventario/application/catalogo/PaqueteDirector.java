package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Setter;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class PaqueteDirector {

	@Setter
	private Integer idPaquete;
	@Setter
	private String codigoPaquete;
	@Setter
	private String descripcion;
	@Setter
	private String nombrePaquete;
	@Setter
	private Double precio;
	@Setter
	private String usuarioCreacion;

	@Setter
	private PaqueteBuilder builder;
	/**
	 * 
	 */
	public PaqueteDirector() {
		// TODO Auto-generated constructor stub
	}
	
	public void construirPaquete() {
		builder.preparar();
		
		builder.setCodigoPaquete(codigoPaquete);
		builder.setDescripcion(descripcion);
		builder.setFechaRegistro(new Date());
		builder.setIdPaquete(idPaquete);
		builder.setNombrePaquete(nombrePaquete);
		builder.setPrecio(precio);
		builder.setUsuarioCreacion(usuarioCreacion);
		
	}


}
