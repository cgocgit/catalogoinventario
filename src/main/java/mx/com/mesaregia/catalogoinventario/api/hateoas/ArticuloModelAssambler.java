package mx.com.mesaregia.catalogoinventario.api.hateoas;

import java.lang.reflect.InvocationTargetException;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.api.ArticuloController;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.dto.ArticuloDTO;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component("articuloControllerAssembler")
public class ArticuloModelAssambler extends AbstractReflectionHateoas<Articulo> {

	/**
	 * @param controller
	 */
	public ArticuloModelAssambler() {
		super(ArticuloController.class);
	}

	@Override
	public EntityModel<Articulo> toModel(Articulo entity) {
		return EntityModel.of(entity);				
	}

	@Override
	public Affordance getAffordance(Articulo modelo, CRUDMethod operacion) {
		switch (operacion) {
		case GET:
			getAffordance("one", modelo.getIdArticulo().intValue());
		case POST:
			getAffordance("registrarArticulo", new ArticuloDTO());
		case PUT:
			return getAffordance("actualizarArticulo", modelo.getIdArticulo().intValue(), new ArticuloDTO());
		case DELETE:
			return getAffordance("bajarArticulo", modelo.getIdArticulo().intValue());
		default:
			break;
		}
		return null;
	}

	@Override
	protected EntityModel<Articulo> setHateoasPatch(Articulo modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityModel<Articulo> setHateoasDelete(Articulo modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityModel<Articulo> setHateoasPut(Articulo modelo) {
		try {
			Link link = linkActualizar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Articulo> paqueteEntity = toModel(modelo);
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
	protected EntityModel<Articulo> setHateoasPost(Articulo modelo) {
		try {
			Link link = linkRegistrar(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.GET))
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Articulo> paqueteEntity = toModel(modelo);
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
	protected EntityModel<Articulo> setHateoasGet(Articulo modelo) {
		try {
			Link link = linkOne(modelo)
			.andAffordance(getAffordance(modelo, CRUDMethod.PUT))
			.andAffordance(getAffordance(modelo, CRUDMethod.DELETE));
			
			EntityModel<Articulo> paqueteEntity = toModel(modelo);
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
	private Link linkEliminar(Articulo modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("bajarArticulo", "bajarArticulo", false, modelo.getIdArticulo().intValue());
	}

	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkActualizar(Articulo modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("actualizarArticulo", "actualizarPaquete", false, modelo.getIdArticulo().intValue(), new ArticuloDTO());
	}

	/**
	 * @param modelo
	 * @return
	 */
	private Link linkRegistrar(Articulo modelo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("registrarArticulo", "registrarArticulo", false, new ArticuloDTO());
	}
	
	/**
	 * @param modelo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Link linkOne(Articulo modelo)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo("one", "obtener", false, modelo.getIdArticulo().intValue());
	}

}
