package mx.com.mesaregia.catalogoinventario.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public abstract class AbstractReflectionHateoas<T> implements RepresentationModelAssembler<T, EntityModel<T>> {

	protected Class<?> controller;

	/**
	 * 
	 */
	public AbstractReflectionHateoas(Class<?> controller) {
		/* Main constraint */
		this.controller = controller;
	}

	public EntityModel<T> toModel(T modelo, CRUDMethod operacion) {
		switch (operacion) {
		case GET:
			return setHateoasGet(modelo);
		case POST:
			return setHateoasPost(modelo);
		case PUT:
			return setHateoasPut(modelo);
		case DELETE:
			return setHateoasDelete(modelo);
		case PATCH:
			return setHateoasPatch(modelo);
		default:
			return null;
		}
	}

	protected Object getController() {
		return methodOn(controller);
	}

	protected Object invoke(Object obj, String metodo, Object... args)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> clazz = obj.getClass();
		Method method = args.length == 0 ? clazz.getMethod(metodo) : clazz.getMethod(metodo, getClass(args));
		return args.length == 0 ? method.invoke(obj) : method.invoke(obj, args);
	}

	/**
	 * @param args
	 * @return
	 */
	private Class<?>[] getClass(Object[] args) {
		Class<?>[] types = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}
		return types;
	}

	protected Link linkToVoid(int id, String rel) {
		return linkTo(controller).slash(id).withRel(rel);
	}

	protected Link linkToMetodo(String metodo, String rel, boolean self, Object...args)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		if (self) {
			return linkTo(invoke(getController(), metodo, args)).withSelfRel();
		}
		return linkTo(invoke(getController(), metodo, args)).withRel(rel);
	}
	
	protected Affordance getAffordance(String metodo, Object...args) {
		try {
			return afford(invoke(getController(), metodo, args));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param modelo
	 * @param operacion
	 * @return
	 */
	public abstract Affordance getAffordance(T modelo, CRUDMethod operacion);

	/**
	 * @param modelo
	 * @return
	 */
	protected abstract EntityModel<T> setHateoasPatch(T modelo);

	/**
	 * @param modelo
	 * @return
	 */
	protected abstract EntityModel<T> setHateoasDelete(T modelo);

	/**
	 * @param modelo
	 * @return
	 */
	protected abstract EntityModel<T> setHateoasPut(T modelo);

	/**
	 * @param modelo
	 * @return
	 */
	protected abstract EntityModel<T> setHateoasPost(T modelo);

	/**
	 * @param modelo
	 * @return
	 */
	protected abstract EntityModel<T> setHateoasGet(T modelo);

}
