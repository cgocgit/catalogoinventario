package mx.com.mesaregia.catalogoinventario.api.hateoas;

import java.lang.reflect.InvocationTargetException;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.api.DetallePaqueteController;
import mx.com.mesaregia.catalogoinventario.dto.DetalleEnPaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.DetallePaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.PaqueteDTO;
import mx.com.mesaregia.catalogoinventario.dto.TipoDetalle;

/**
 * Hypermedia as the Engine of Application State for Controller elementos en Paquete.
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component("detallePaqueteControllerAssembler")
public class DetallePaqueteModelAssambler extends AbstractReflectionHateoas<DetalleEnPaqueteDTO> {

	/**
	 * 
	 */
	private static final String QUITARSERVICIODELPAQUETE = "quitarServicioDelPaquete";
	/**
	 * 
	 */
	private static final String QUITARARTICULODELPAQUETE = "quitarArticuloDelPaquete";
	/**
	 * 
	 */
	private static final String REGISTRARSERVICIO = "registrarServicio";
	/**
	 * 
	 */
	private static final String REGISTRARARTICULO = "registrarArticulo";
	/**
	 * 
	 */
	private static final String GETDETALLES = "getDetalles";
	/**
	 * 
	 */
	private static final String ACTUALIZARPAQUETE = "actualizarPaquete";

	/**
	 * @param controller
	 */
	public DetallePaqueteModelAssambler() {
		super(DetallePaqueteController.class);
	}

	@Override
	public EntityModel<DetalleEnPaqueteDTO> toModel(DetalleEnPaqueteDTO entity) {
		return EntityModel.of(entity);				
	}

	@Override
	public Affordance getAffordance(DetalleEnPaqueteDTO modelo, CRUDMethod operacion) {
		switch (operacion) {
		case GET:
			return getAffordance(GETDETALLES, modelo.getIdPaquete());
		case POST:
			if (TipoDetalle.ARTICULO.equals(modelo.getTipoDetalle()))
				return getAffordance(REGISTRARARTICULO, new DetallePaqueteDTO());
			else
				return getAffordance(REGISTRARSERVICIO, new DetallePaqueteDTO());
		case PUT:
			return getAffordance(ACTUALIZARPAQUETE, modelo.getIdPaquete(), new PaqueteDTO());
		case DELETE:
			if (TipoDetalle.ARTICULO.equals(modelo.getTipoDetalle()))
				return getAffordance(QUITARARTICULODELPAQUETE, modelo.getIdDetallePaquete());
			else
				return getAffordance(QUITARSERVICIODELPAQUETE, modelo.getIdDetallePaquete());
		default:
			break;
		}
		return null;
	}

	@Override
	protected EntityModel<DetalleEnPaqueteDTO> setHateoasPatch(DetalleEnPaqueteDTO modelo) {
		return null;
	}

	@Override
	protected EntityModel<DetalleEnPaqueteDTO> setHateoasDelete(DetalleEnPaqueteDTO modelo) {
		return null;
	}

	@Override
	protected EntityModel<DetalleEnPaqueteDTO> setHateoasPut(DetalleEnPaqueteDTO modelo) {
		try {
			Link link = linkActualizar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<DetalleEnPaqueteDTO> paqueteEntity = toModel(modelo);
			paqueteEntity.add(link);
			paqueteEntity.add(linkOne(modelo));
			paqueteEntity.add(linkEliminar(modelo));
			
			return paqueteEntity;	
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected EntityModel<DetalleEnPaqueteDTO> setHateoasPost(DetalleEnPaqueteDTO modelo) {
		try {
			Link link = linkRegistrar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<DetalleEnPaqueteDTO> paqueteEntity = toModel(modelo);
			paqueteEntity.add(link);
			paqueteEntity.add(linkOne(modelo));
			paqueteEntity.add(linkEliminar(modelo));
			
			return paqueteEntity;			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected EntityModel<DetalleEnPaqueteDTO> setHateoasGet(DetalleEnPaqueteDTO modelo) {
		try {
			Link link = linkEliminar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<DetalleEnPaqueteDTO> paqueteEntity = toModel(modelo);
			paqueteEntity.add(link);
			
			return paqueteEntity;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkEliminar(DetalleEnPaqueteDTO modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (TipoDetalle.ARTICULO.equals(modelo.getTipoDetalle()))
			return linkToMetodo(QUITARARTICULODELPAQUETE, QUITARARTICULODELPAQUETE, false, modelo.getIdDetallePaquete());
		else
			return linkToMetodo(QUITARSERVICIODELPAQUETE, QUITARSERVICIODELPAQUETE, false, modelo.getIdDetallePaquete());
	}

	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkActualizar(DetalleEnPaqueteDTO modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(ACTUALIZARPAQUETE, ACTUALIZARPAQUETE, false, modelo.getIdPaquete(), new PaqueteDTO());
	}

	/**
	 * @param modelo
	 * @return
	 */
	private Link linkRegistrar(DetalleEnPaqueteDTO modelo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (TipoDetalle.ARTICULO.equals(modelo.getTipoDetalle()))
			return linkToMetodo(REGISTRARARTICULO, REGISTRARARTICULO, false, new DetallePaqueteDTO());
		else
			return linkToMetodo(REGISTRARSERVICIO, REGISTRARSERVICIO, false, new DetallePaqueteDTO());
	}
	
	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkOne(DetalleEnPaqueteDTO modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(GETDETALLES, "obtener", false, modelo.getIdPaquete());
	}

}
