package mx.com.mesaregia.catalogoinventario.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import mx.com.mesaregia.catalogoinventario.domain.Servicio;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Component
public class ServicioModelAssambler extends AbstractHateoas<Servicio> {
//implements RepresentationModelAssembler<Servicio, EntityModel<Servicio>> {

	private static final Class<ServicioController> CONTROLLERTYPESERVICIO = ServicioController.class;

	/**
	 * 
	 */
	public ServicioModelAssambler() {
		/* main constructor */
	}

	@Override
	public EntityModel<Servicio> toModel(Servicio servicio) {

		return EntityModel.of(servicio,
				linkGetOne(servicio.getIdServicio()).andAffordance(getAffordUpdate(servicio.getIdServicio()))
						.andAffordance(getAffordDelete(servicio.getIdServicio())),
				linkPut(servicio.getIdServicio()), linkDelete(servicio.getIdServicio()));

	}

	/**
	 * @param idServicio
	 * @return
	 */
	private Affordance getAffordDelete(Integer id) {
		return afford(methodOn(CONTROLLERTYPESERVICIO).bajarServicio(id));
	}

	private Affordance getAffordUpdate(int id) {
		return afford(methodOn(CONTROLLERTYPESERVICIO).actualizarServicios(id, null));
	}

	private Affordance getAffordCreate() {
		return afford(methodOn(CONTROLLERTYPESERVICIO).registrarServicios(null));
	}

	private Link linkGetOne(int id) {
		return linkTo(methodOn(CONTROLLERTYPESERVICIO).one(id)).withRel("Obtener");
	}

	private Link linkPut(int id) {
		return linkTo(methodOn(CONTROLLERTYPESERVICIO).actualizarServicios(id, null)).withRel("Actualizacion");
	}

	private Link linkDelete(int id) {
		return linkTo(CONTROLLERTYPESERVICIO).slash(id).withRel("Delete");
	}

	private Link linkToCreate() {
		return linkTo(methodOn(CONTROLLERTYPESERVICIO).registrarServicios(null)).withRel("Registrar");
	}

	@Override
	protected Link linkToPost(Servicio model) {
		return linkToCreate();
	}

	@Override
	protected Link linkToGet(Servicio model) {
		return linkGetOne(model.getIdServicio());
	}

	@Override
	protected Link linkToDelete(Servicio model) {
		return linkDelete(model.getIdServicio());
	}

	@Override
	protected Link linkToPut(Servicio model) {
		return linkPut(model.getIdServicio());
	}

	@Override
	protected Link linkToPatch(Servicio model) {
		return null;
	}

	@Override
	protected Affordance affordPost(Servicio model) {
		return getAffordCreate();
	}

	@Override
	protected Affordance affordGet(Servicio model) {
		return null;
	}

	@Override
	protected Affordance affordDelete(Servicio model) {
		return getAffordDelete(model.getIdServicio());
	}

	@Override
	protected Affordance affordPut(Servicio model) {
		return getAffordUpdate(model.getIdServicio());
	}

	@Override
	protected Affordance affordPatch(Servicio model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
		public EntityModel<Void> toModel(int id, CRUDMethod operacion) {
		switch (operacion) {
		case DELETE:
			return setHateoasDelete(id);
		default:
			break;
		}
		return null;
		}

	/**
	 * @param id
	 * @return
	 */
	private EntityModel<Void> setHateoasDelete(int id) {
		EntityModel<Void> model = EntityModel.of(null);
		model.add(linkDelete(id));
		return model;
	}

	@Override
	public EntityModel<Servicio> toModel(Servicio model, CRUDMethod operacion) {
		switch (operacion) {
		case POST:
			return setHateoasPost(model);
		case PUT:
			return setHateoasPut(model);
		default:
			break;
		}
		return null;
	}

	/**
	 * @param model
	 * @return
	 */
	private EntityModel<Servicio> setHateoasPut(Servicio model) {
		return EntityModel.of(model,
				linkTo(methodOn(CONTROLLERTYPESERVICIO).actualizarServicios(model.getIdServicio(), null)).withSelfRel()
						.andAffordance(getAffordDelete(model.getIdServicio())),
				linkGetOne(model.getIdServicio()), linkDelete(model.getIdServicio()));
	}

	/**
	 * @param model
	 */
	private EntityModel<Servicio> setHateoasPost(Servicio model) {
		return EntityModel.of(model,
				linkTo(methodOn(CONTROLLERTYPESERVICIO).registrarServicios(null)).withSelfRel()
						.andAffordance(getAffordUpdate(model.getIdServicio()))
						.andAffordance(getAffordDelete(model.getIdServicio())),
				linkGetOne(model.getIdServicio()), linkPut(model.getIdServicio()), linkDelete(model.getIdServicio()));
	}

}
