package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Date;

import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class ArticuloBuilderImpl implements ArticuloBuilder {

	private Articulo articulo;
	
	/**
	 * 
	 */
	public ArticuloBuilderImpl() {
		/* Constructor principal */
	}

	@Override
	public void setCategoria(Categoria categoria) {
		articulo.setCategoria(categoria);
	}

	@Override
	public void setColor(Color color) {
		articulo.setColor(color);
	}

	@Override
	public void setDescripcion(String descripcion) {
		articulo.setDescripcionArticulo(descripcion);
	}

	@Override
	public void setFechaRegistro() {
		articulo.setFechaRegistro(new Date());
	}

	@Override
	public void setNombre(String nombre) {
		articulo.setNombreArticulo(nombre);
	}

	@Override
	public void setTipo(TipoArticulo tipoArticulo) {
		articulo.setTipoArticulo(tipoArticulo);
	}

	@Override
	public void setUnidadMedida(String unidadMedida) {
		articulo.setUnidadMedida(unidadMedida);
	}

	@Override
	public void setUsuario(String usuario) {
		articulo.setUsuarioRegistro(usuario);
	}

	@Override
	public Articulo get() {
		return articulo;
	}

	@Override
	public void preparar() {
		articulo = new Articulo();
	}

	@Override
	public void setIdArticulo(Long idArticulo) {
		articulo.setIdArticulo(idArticulo);
	}

}
