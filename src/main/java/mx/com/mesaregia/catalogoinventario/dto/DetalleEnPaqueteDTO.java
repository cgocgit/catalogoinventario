package mx.com.mesaregia.catalogoinventario.dto;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Transferencia de datos entre la entidad DetallePaquete (Articulos, Servicios)
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetalleEnPaqueteDTO {

	private int idDetallePaquete;
	
	private int idPaquete;
	private String codigoPaquete;
	
	/**
	 * Articulo o Servicio
	 */
	private TipoDetalle tipoDetalle;
	
	/**
	 * Identificador del articulo o servicio
	 */
	private int idArticuloServicio;
	/**
	 * Codigo del articulo o servicio
	 */
	private String codigo;
	
	/**
	 * Cantidad del articulo
	 */
	private int cantidad;
	/**
	 * Precio del servicio
	 */
	private double precio;
	/**
	 * Tarifa base del servicio
	 */
	private double tarifaBase;
	
	/**
	 * Nombre del articulo o servicio
	 */
	private String nombreDetalle;
	/**
	 * Descripcion del articulo o servicio
	 */
	private String descripcion;
	/**
	 * Tipo de articulo o servicio
	 */
	private String tipo;
	/**
	 * Categoria del articulo o servicio
	 */
	private String categoria;
	/**
	 * Color del articulo
	 */
	private String color;
	/**
	 * Unidad de medida del articulo
	 */
	private String unidadMedida;
	
	/**
	 * 
	 */
	protected DetalleEnPaqueteDTO() {
	}

}
