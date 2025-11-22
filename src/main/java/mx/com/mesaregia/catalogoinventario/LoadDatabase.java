/**
 * 
 */
package mx.com.mesaregia.catalogoinventario;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.Categoria;
import mx.com.mesaregia.catalogoinventario.domain.Color;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteArticulo;
import mx.com.mesaregia.catalogoinventario.domain.DetallePaqueteServicio;
import mx.com.mesaregia.catalogoinventario.domain.Estado;
import mx.com.mesaregia.catalogoinventario.domain.NivelControl;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.domain.Servicio;
import mx.com.mesaregia.catalogoinventario.domain.TipoArticulo;
import mx.com.mesaregia.catalogoinventario.domain.TipoCategoria;
import mx.com.mesaregia.catalogoinventario.domain.TipoServicio;
import mx.com.mesaregia.catalogoinventario.repository.AlmacenRepository;
import mx.com.mesaregia.catalogoinventario.repository.ArticuloRepository;
import mx.com.mesaregia.catalogoinventario.repository.CategoriaRepository;
import mx.com.mesaregia.catalogoinventario.repository.ColorRepository;
import mx.com.mesaregia.catalogoinventario.repository.DetallePaqueteArticuloRepository;
import mx.com.mesaregia.catalogoinventario.repository.DetallePaqueteServicioRepository;
import mx.com.mesaregia.catalogoinventario.repository.PaqueteRepository;
import mx.com.mesaregia.catalogoinventario.repository.ServicioRepository;
import mx.com.mesaregia.catalogoinventario.repository.TipoArticuloRepository;

/**
 * Clase de configuración para carga de datos dummy.
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Configuration
public class LoadDatabase {

	/**
	 * 
	 */
	private static final String PRELOADING = "Preloading: {}";
	/**
	 * 
	 */
	private static final String COLOR = "Color: {}";
	/**
	 * 
	 */
	private static final String CATEGORIA = "Categoria: {}";
	/**
	 * 
	 */
	private static final String TIPO_ARTICULO = "TipoArticulo: {}";
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	
	@Bean
	CommandLineRunner initDataBaseArticulo(ArticuloRepository articuloRepository,
			CategoriaRepository categoriaRepository, ColorRepository colorRepository,
			TipoArticuloRepository tipoArticuloRepository, ServicioRepository servicioRepository, PaqueteRepository paqueteRepository,
			DetallePaqueteArticuloRepository detallePaqueteArticuloRepository, DetallePaqueteServicioRepository detallePaqueteServicioRepository,
			AlmacenRepository almacenRepository) {
	
		return args -> {
			
			log.info("");
			log.info("################   initDataBaseArticulo #########################################################################################");
			log.info("");
		
			Color blanco = new Color(1, "Blanco", "#ffffff", "Blanco");
			log.info(PRELOADING, colorRepository.saveAndFlush(blanco));
			TipoArticulo tipoArticulo = new TipoArticulo(1, "Silla", "Describe Silla", NivelControl.ALTO);
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoArticulo));
			Categoria categoria = new Categoria(1, "Silla", "Describe categoria", TipoCategoria.PRODUCTO);
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoria));

			log.info(TIPO_ARTICULO, tipoArticulo);
			log.info(CATEGORIA, categoria);
			log.info(COLOR, blanco);

			Date fechaRegistro = new Date();
			Articulo articulo = new Articulo(null, "Articulo", "Describe Articulo", tipoArticulo, categoria, blanco,
					"cantidad", true, fechaRegistro, "Desarrollador");
			log.info("Articulo: {}", articulo);
			log.info(PRELOADING, articuloRepository.saveAndFlush(articulo));
			
			log.info("");
			log.info("################   initDataBaseArticulo #########################################################################################");
			log.info("");
			log.info("");
			log.info("################   initDataBaseServicio #########################################################################################");
			log.info("");
			
			Categoria categoria2 = new Categoria(2, "Servicios de buffet", "Describe categoria", TipoCategoria.SERVICIO);
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoria2));
			log.info(CATEGORIA, categoria2);
			Servicio servicio = new Servicio(null, "SrvBuffet", "Catering", "Desayuno intercontinental",
					TipoServicio.BANQUETE, 5678.90, true, 5555d, categoria2, new Date(), "Por miguelito", null, null);
			log.info("Servicio: {}", servicio);
			log.info(PRELOADING, servicioRepository.saveAndFlush(servicio));

			log.info("");
			log.info("################   initDataBaseServicio #########################################################################################");
			log.info("");

			log.info("");
			log.info("################   initDataBasePaquete  #########################################################################################");
			log.info("");
			Articulo articuloP = articuloRepository.findById(1).orElse(null);
			Servicio servicioP = servicioRepository.findById(1).orElse(null);

			Paquete paquete = new Paquete(null, "Paquete uno", "Una mesa 10 Sillas", 700d, true, "paqUno", new Date(),
					"ADMNISTRADOR");
			log.info("Servicio: {}", paquete);
			log.info(PRELOADING, paqueteRepository.saveAndFlush(paquete));
			
			DetallePaqueteArticulo detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete, articuloP, 10l, 30d);
			log.info("Detalle articulo en paquete: {}", detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			DetallePaqueteServicio detallePaqueteServicio = new DetallePaqueteServicio(null, paquete, servicioP, 1, 3560d);
			log.info("Detalle servicio en paquete: {}", detallePaqueteServicio);
			log.info(PRELOADING, detallePaqueteServicioRepository.saveAndFlush(detallePaqueteServicio));

			log.info("");
			log.info("################   initDataBasePaquete  #########################################################################################");
			log.info("");
			

			log.info("");
			log.info("################   initDataBaseConsultaPaquetes  #########################################################################################");
			log.info("");
			List<DetallePaqueteArticulo> detalleArticulos = detallePaqueteArticuloRepository.findByIdPaquete(1);
			log.info("Los detalles de articulo: {}", detalleArticulos.size());
			detalleArticulos.forEach(d -> log.info(d.toString()));
			

			List<DetallePaqueteServicio> detalleServicio = detallePaqueteServicioRepository.findByIdPaquete(1);
			log.info("Los detalles de servicio: {}", detalleServicio.size());
			detalleServicio.forEach(d -> log.info(d.toString()));
			log.info("");
			log.info("################   initDataBaseConsultaPaquetes  #########################################################################################");
			log.info("");

			
			log.info("");
			log.info("################   initDataBaseAlmacen  #########################################################################################");
			log.info("");

			Almacen almacen = new Almacen(1, "Las Lomas", "madeira 358", "Skarlet", 200, Estado.ACTIVO);
			log.info(almacen.toString());
			log.info(PRELOADING, almacenRepository.saveAndFlush(almacen));
			log.info(almacen.toString());
			log.info("");
			log.info("################   initDataBaseAlmacen  #########################################################################################");
			log.info("");
		};
		
		
	}
	

}
