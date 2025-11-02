package mx.com.mesaregia.catalogoinventario;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
@OpenAPIDefinition (
		info=@Info(
				title = "Modulo de Catalogo e Inventario",
				description = "Adminitración del catalogo de productos y servicios, e inventario de los mismos.",
				termsOfService = "www.mesaregia.com.mx/terminos_y_servicios",
				contact = @Contact(
						name = "Joselin Paola Olvera Almeida",
						url = "www.mesaregia.com.mx/contacto",
						email = "contacto@mesaregia.com.mx"),
				license = @License (
						name = "Standar Software Use License for Mesa Regia",
						url = "www.mesaregia.com.mx/license"
						)
				
				),
		servers = {
				@Server(
						description = "DEV SERVER",
						url = "http://localhost:8080"
						),
				@Server(
						description = "PROD SERVER",
						url = "http://mesaregia.com.mx"
						)
				
		}
		)
public class SwaggerConfig {

	/**
	 * 
	 */
	public SwaggerConfig() {/*Constructor Principal*/}

}
