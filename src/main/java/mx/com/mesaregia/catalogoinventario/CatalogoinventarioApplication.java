package mx.com.mesaregia.catalogoinventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * Aplicativo Spring Boot
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@SpringBootApplication
@EnableHypermediaSupport(type = HypermediaType.HAL_FORMS)
public class CatalogoinventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoinventarioApplication.class, args);
	}

}
