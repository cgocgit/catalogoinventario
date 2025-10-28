/**
 * 
 */
package mx.com.mesaregia.catalogoinventario.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Stock de articulos en un almacen.
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
public class StockAlmacen {

	@Id
	private Integer idStock;
	@OneToOne
	@JoinColumn(name = "idAlmacen", referencedColumnName = "idAlmacen")
	private Almacen almacen;
	@OneToOne
	@JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo")
	private Articulo articulo;
	private long cantidad;

	/**
	 * 
	 */
	public StockAlmacen() {
		/* Cfonstructor principal */
	}

	public StockAlmacen(Integer idStock, Almacen almacen, Articulo articulo, long cantidad) {
		super();
		this.idStock = idStock;
		this.almacen = almacen;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

}
