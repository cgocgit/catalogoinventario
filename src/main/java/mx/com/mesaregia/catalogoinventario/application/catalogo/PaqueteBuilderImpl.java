package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Date;

import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.domain.Paquete;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component
public class PaqueteBuilderImpl implements PaqueteBuilder {

	private Paquete paquete;
	
	/**
	 * 
	 */
	public PaqueteBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Paquete get() {
		return this.paquete;
	}

	@Override
	public void preparar() {
		this.paquete = new Paquete();
	}

	@Override
	public void setCodigoPaquete(String codiPaquete) {
		this.paquete.setCodigoPaquete(codiPaquete);
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.paquete.setDescripcion(descripcion);
	}

	@Override
	public void setNombrePaquete(String nombrePaquete) {
		this.paquete.setNombrePaquete(nombrePaquete);
	}

	@Override
	public void setPrecio(Double precio) {
		this.paquete.setPrecio(precio);
	}

	@Override
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.paquete.setUsuarioCreacion(usuarioCreacion);
	}

	@Override
	public void setIdPaquete(Integer idPaquete) {
		this.paquete.setIdPaquete(idPaquete);
	}

	@Override
	public void setFechaRegistro(Date fechaRegistro) {
		this.paquete.setFechaRegistro(fechaRegistro);
	}

}
