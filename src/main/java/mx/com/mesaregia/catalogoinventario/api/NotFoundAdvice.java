package mx.com.mesaregia.catalogoinventario.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Manejador de excepciones para elementos no encontrados.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
public class NotFoundAdvice {

	 @ExceptionHandler(NotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String notFoundHandler(NotFoundException ex) {
	    return ex.getMessage();
	  }

}
