package mx.com.mesaregia.catalogoinventario.api;

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
public abstract class AbstractHateoas<T> implements RepresentationModelAssembler<T, EntityModel<T>> {

	/**
	 * 
	 */
	public AbstractHateoas() {
		/*Main constructor */
	}
	
	public abstract EntityModel<T> toModel(T model, CRUDMethod operacion);
	
	public abstract EntityModel<Void> toModel(int id, CRUDMethod operacion);
	
	protected abstract Affordance affordPost(T model);
	protected abstract Affordance affordGet(T model);
	protected abstract Affordance affordDelete(T model);
	protected abstract Affordance affordPut(T model);
	protected abstract Affordance affordPatch(T model);
	
	protected abstract Link linkToPost(T model);
	protected abstract Link linkToGet(T model);
	protected abstract Link linkToDelete(T model);
	protected abstract Link linkToPut(T model);
	protected abstract Link linkToPatch(T model);
	
	


}
