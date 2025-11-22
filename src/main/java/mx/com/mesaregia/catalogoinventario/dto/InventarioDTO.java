package mx.com.mesaregia.catalogoinventario.dto;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InventarioDTO {

	private Integer idInventario;
	
	private Long idArticulo;
	private String nombreArticulo;
	private String descripcionArticulo;
	
	private long cantidadActual;
	private long stockMinimo;
	private long stockMaximo;
	private Date fechaActualizacion;
	private String usuarioActualizacion;
	
	private Integer idAlmacen;
	private String nombreAlmacen;
	
	/**
	 * 
	 */
	protected InventarioDTO() {
	}

}
