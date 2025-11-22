package mx.com.mesaregia.catalogoinventario.api.hateoas;

import java.lang.reflect.InvocationTargetException;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.api.PaqueteController;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.dto.PaqueteDTO;

/**
 * Hypermedia as the Engine of Application State for Controller Paquete.
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component("paqueteControllerAssembler")
public class PaqueteModelAssambler extends AbstractReflectionHateoas<Paquete> {

	/**
	 * 
	 */
	private static final String BAJARPAQUETE = "bajarPaquete";
	/**
	 * 
	 */
	private static final String ACTUALIZARPAQUETE = "actualizarPaquete";
	/**
	 * 
	 */
	private static final String REGISTRARPAQUETE = "registrarPaquete";

	/**
	 * @param controller
	 */
	public PaqueteModelAssambler() {
		super(PaqueteController.class);
	}

	@Override
	public EntityModel<Paquete> toModel(Paquete entity) {
		return EntityModel.of(entity);				
	}

	@Override
	public Affordance getAffordance(Paquete modelo, CRUDMethod operacion) {
		switch (operacion) {
		case GET:
			return getAffordance("one", modelo.getIdPaquete());
		case POST:
			return getAffordance(REGISTRARPAQUETE, new PaqueteDTO());
		case PUT:
			return getAffordance(ACTUALIZARPAQUETE, modelo.getIdPaquete(), new PaqueteDTO());
		case DELETE:
			return getAffordance(BAJARPAQUETE, modelo.getIdPaquete());
		default:
			break;
		}
		return null;
	}

	@Override
	protected EntityModel<Paquete> setHateoasPatch(Paquete modelo) {
		return null;
	}

	@Override
	protected EntityModel<Paquete> setHateoasDelete(Paquete modelo) {
		return null;
	}

	@Override
	protected EntityModel<Paquete> setHateoasPut(Paquete modelo) {
		try {
			Link link = linkActualizar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Paquete> paqueteEntity = toModel(modelo);
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
	protected EntityModel<Paquete> setHateoasPost(Paquete modelo) {
		try {
			Link link = linkRegistrar()
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Paquete> paqueteEntity = toModel(modelo);
			paqueteEntity.add(link);
			paqueteEntity.add(linkOne(modelo));
			paqueteEntity.add(linkActualizar(modelo));
			paqueteEntity.add(linkEliminar(modelo));
			
			return paqueteEntity;			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected EntityModel<Paquete> setHateoasGet(Paquete modelo) {
		try {
			Link link = linkOne(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Paquete> paqueteEntity = toModel(modelo);
			paqueteEntity.add(link);
			paqueteEntity.add(linkActualizar(modelo));
			paqueteEntity.add(linkEliminar(modelo));
			
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
	private Link linkEliminar(Paquete modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(BAJARPAQUETE, BAJARPAQUETE, false, modelo.getIdPaquete());
	}

	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkActualizar(Paquete modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(ACTUALIZARPAQUETE, ACTUALIZARPAQUETE, false, modelo.getIdPaquete(), new PaqueteDTO());
	}

	/**
	 * @param modelo
	 * @return
	 */
	private Link linkRegistrar() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(REGISTRARPAQUETE, REGISTRARPAQUETE, false, new PaqueteDTO());
	}
	
	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkOne(Paquete modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("one", "obtener", false, modelo.getIdPaquete());
	}

}
