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
 * Hypermedia as the Engine of Application State for Controller Articulos.
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@Component("articuloControllerAssembler")
public class ArticuloModelAssambler extends AbstractReflectionHateoas<Articulo> {

	/**
	 * 
	 */
	private static final String ONE = "one";
	/**
	 * 
	 */
	private static final String REGISTRARARTICULO = "registrarArticulo";
	/**
	 * 
	 */
	private static final String ACTUALIZARARTICULO = "actualizarArticulo";
	/**
	 * 
	 */
	private static final String BAJARARTICULO = "bajarArticulo";

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
			return getAffordance(ONE, modelo.getIdArticulo().intValue());
		case POST:
			return getAffordance(REGISTRARARTICULO, new ArticuloDTO());
		case PUT:
			return getAffordance(ACTUALIZARARTICULO, modelo.getIdArticulo().intValue(), new ArticuloDTO());
		case DELETE:
			return getAffordance(BAJARARTICULO, modelo.getIdArticulo().intValue());
		default:
			break;
		}
		return null;
	}

	@Override
	protected EntityModel<Articulo> setHateoasPatch(Articulo modelo) {
		return null;
	}

	@Override
	protected EntityModel<Articulo> setHateoasDelete(Articulo modelo) {
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
			Link link = linkRegistrar()
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
		return linkToMetodo(BAJARARTICULO, BAJARARTICULO, false, modelo.getIdArticulo().intValue());
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
		return linkToMetodo(ACTUALIZARARTICULO, "actualizarPaquete", false, modelo.getIdArticulo().intValue(), new ArticuloDTO());
	}

	/**
	 * @param modelo
	 * @return
	 */
	private Link linkRegistrar() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return linkToMetodo(REGISTRARARTICULO, REGISTRARARTICULO, false, new ArticuloDTO());
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
		return linkToMetodo(ONE, "obtener", false, modelo.getIdArticulo().intValue());
	}

}
