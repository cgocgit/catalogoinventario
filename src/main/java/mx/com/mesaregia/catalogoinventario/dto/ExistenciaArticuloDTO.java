package mx.com.mesaregia.catalogoinventario.dto;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import mx.com.mesaregia.catalogoinventario.domain.EstadoArticulo;

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
public class ExistenciaArticuloDTO {

	private Long idArticulo;
	private String nombreArticulo;
	private String descripcionArticulo;
	
	private Integer idAlmacen;
	private String nombreAlmacen;
	
	private EstadoArticulo estado;
	
	/**
	 * 
	 */
	protected ExistenciaArticuloDTO() {

	}

}
