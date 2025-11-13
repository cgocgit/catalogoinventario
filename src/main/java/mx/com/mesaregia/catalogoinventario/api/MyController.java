package mx.com.mesaregia.catalogoinventario.api;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;



@RestController
public class MyController {


	/**
	 * 
	 */
	public MyController() {
		// TODO Auto-generated constructor stub
	}

	
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        String message = "Hello from Spring Boot!";
        return new ResponseEntity<>(message, HttpStatus.OK); // Returns "Hello from Spring Boot!" with 200 OK status
    }

    @GetMapping("/created")
    public ResponseEntity<Void> resourceCreated() {
        return new ResponseEntity<>(HttpStatus.CREATED); // Returns 201 Created status with no body
    }

    @GetMapping("/notfound")
    public ResponseEntity<String> resourceNotFound() {
        String errorMessage = "Resource not found.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND); // Returns "Resource not found." with 404 Not Found status
    }
    
    @GetMapping("/employees/{id}")
    public EntityModel<Servicio> findOne(@PathVariable Integer id) {
      
//      Servicio servicio = new Servicio(1, "123", "Servoio", "descripcion", TipoServicio.Banquete, 1d, false, 2d, null);
//      Link self = linkTo(methodOn(MyController.class).findOne(id)).withSelfRel();
//      Link createLink = Link.of(linkTo(methodOn(MyController.class).partiallyUpdateEmployee(null, id)).toUri().toString(), "create").withType("PUT");
      Link updateLink = Link.of(linkTo(methodOn(MyController.class).updateEmployee(null, id)).toUri().toString(), "update").withType("PATCH");
//      return EntityModel.of(servicio, self, updateLink, createLink);
    	Servicio servicio = new Servicio(1, "123", "Servoio", "descripcion",
    	            TipoServicio.Banquete, 1d, false, 2d, null);
    	 
    	EntityModel<Servicio> model = EntityModel.of(servicio,
    		    linkTo(methodOn(MyController.class).findOne(id)).withSelfRel()
    		        .andAffordance(afford(methodOn(MyController.class).updateEmployee(null, id)))
    		        .andAffordance(afford(methodOn(MyController.class).partiallyUpdateEmployee(null, id))),
    		    updateLink = Link.of(linkTo(methodOn(MyController.class).updateEmployee(null, id)).toUri().toString(), "update").withType("PATCH")
    		);
    	
    	return model;

    }
    
    @PutMapping("/employees/{id}")
    public ResponseEntity<Servicio> updateEmployee( //
        @RequestBody Servicio employee, @PathVariable Integer id) {
    	 return new ResponseEntity<>(HttpStatus.CREATED); // Returns 201 Created status with no body
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<Servicio> partiallyUpdateEmployee( //
        @RequestBody EntityModel<Servicio> employee, @PathVariable Integer id) {
    	 return new ResponseEntity<>(HttpStatus.CREATED); // Returns 201 Created status with no body
    }



}