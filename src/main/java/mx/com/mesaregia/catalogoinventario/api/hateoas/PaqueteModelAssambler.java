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
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component("paqueteControllerAssembler")
public class PaqueteModelAssambler extends AbstractReflectionHateoas<Paquete> {

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
			getAffordance("one", modelo.getIdPaquete().intValue());
		case POST:
			getAffordance("registrarPaquete", new PaqueteDTO());
		case PUT:
			return getAffordance("actualizarPaquete", modelo.getIdPaquete().intValue(), new PaqueteDTO());
		case DELETE:
			return getAffordance("bajarPaquete", modelo.getIdPaquete().intValue());
		default:
			break;
		}
		return null;
	}

	@Override
	protected EntityModel<Paquete> setHateoasPatch(Paquete modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityModel<Paquete> setHateoasDelete(Paquete modelo) {
		// TODO Auto-generated method stub
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
			Link link = linkRegistrar(modelo)
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
		return linkToMetodo("bajarPaquete", "bajarPaquete", false, modelo.getIdPaquete().intValue());
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
		return linkToMetodo("actualizarPaquete", "actualizarPaquete", false, modelo.getIdPaquete().intValue(), new PaqueteDTO());
	}

	/**
	 * @param modelo
	 * @return
	 */
	private Link linkRegistrar(Paquete modelo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("registrarPaquete", "registrarPaquete", false, new PaqueteDTO());
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
		return linkToMetodo("one", "obtener", false, modelo.getIdPaquete().intValue());
	}

}
