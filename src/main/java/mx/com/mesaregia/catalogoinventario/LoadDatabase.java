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
	private static final String MEDIDAPIEZA = "pieza";
	/**
	 * 
	 */
	private static final String USERSYSTEM = "system";
	/**
	 * 
	 */
	private static final String ARTICULO = "Articulo: {}";
	/**
	 * 
	 */
	private static final String SERVICIO2 = "Servicio: {}";
	/**
	 * 
	 */
	private static final String DETALLE_SERVICIO_EN_PAQUETE = "Detalle servicio en paquete: {}";
	/**
	 * 
	 */
	private static final String DETALLE_ARTICULO_EN_PAQUETE = "Detalle articulo en paquete: {}";
	/**
	 * 
	 */
	private static final String PRELOADING = "Preloading: {}";
	/**
	 * 
	 */
	private static final String CATEGORIA = "Categoria: {}";
	
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
			
			Color blanco = new Color(1, "Blanco", "#FFFFFF", "Color blanco aplicado a las Mesas Avant Garden");

			Color beige = new Color(2, "Beige claro", "#F5F5DC", "Color beige claro utilizado en la Silla Lifetime");

			Color aceroInoxidable = new Color(3, "Acero inoxidable", "#C0C0C0",
					"Tono gris metálico característico del acero inoxidable");
			
			Color colorSinDefinir = new Color(0, "Sin color", "#FFFFFF", "Color no especificado");
	
			log.info(PRELOADING, colorRepository.saveAndFlush(blanco));
			log.info(PRELOADING, colorRepository.saveAndFlush(beige));
			log.info(PRELOADING, colorRepository.saveAndFlush(aceroInoxidable));
			log.info(PRELOADING, colorRepository.saveAndFlush(colorSinDefinir));
			

			Categoria categoriaProducto = new Categoria(1, "Productos Generales", "Artículos en renta",
					TipoCategoria.PRODUCTO);
			
			Categoria categoriaSilla = new Categoria(1, "Silla",
					"Categoría para artículos de tipo silla utilizados en eventos.", TipoCategoria.PRODUCTO);

			// Categoría para Mesas
			Categoria categoriaMesa = new Categoria(2, "Mesa",
					"Categoría para mesas plegables, ya sean redondas o rectangulares.", TipoCategoria.PRODUCTO);

			// Categoría para Climatización
			Categoria categoriaClimatizacion = new Categoria(3, "Climatización",
					"Artículos destinados a controlar la temperatura en eventos, como calentadores.",
					TipoCategoria.PRODUCTO);
			
			// Categoría para servicios
			Categoria categoriaServicio = new Categoria(2, "Servicios Generales", "Servicios adicionales para eventos",
					TipoCategoria.SERVICIO);

			
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoriaSilla));
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoriaMesa));
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoriaClimatizacion));
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoriaProducto));
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoriaServicio));

			TipoArticulo tipoArticuloMobiliario = new TipoArticulo(1, "Mobiliario",
					"Artículos de mobiliario como sillas, mesas y elementos físicos utilizados en eventos.",
					NivelControl.MEDIO // o el nivel que corresponda según tu catálogo
			);
			
			TipoArticulo tipoArticuloEquipo = new TipoArticulo(2, "Equipo", "Artículos clasificados como equipo funcional para operación o apoyo del evento.",
					NivelControl.BAJO // o el nivel adecuado
			);
			
			TipoArticulo tipoRopaMesa = new TipoArticulo(10, "Ropa de Mesa", "Artículos textiles para mesa",
					NivelControl.BAJO);
			TipoArticulo tipoLoza = new TipoArticulo(11, "Loza", "Artículos de loza para servicio de comida",
					NivelControl.MEDIO);
			TipoArticulo tipoCristaleria = new TipoArticulo(12, "Cristalería", "Artículos de vidrio para servicio",
					NivelControl.MEDIO);
			
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoArticuloMobiliario));
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoArticuloEquipo));
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoRopaMesa));
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoLoza));
			log.info(PRELOADING, tipoArticuloRepository.saveAndFlush(tipoCristaleria));
			
			Date fechaRegistro = new Date();
			
			Articulo sillaLifetime = new Articulo(null, "Silla Lifetime",
					"Silla plegable, liviana y resistente. Ideales y muy cómodas para eventos familiares, asados, cumpleaños, reuniones de amigos, etc.",
					tipoArticuloMobiliario, // Reemplazar con tu instancia real
					categoriaSilla, // Reemplazar
					blanco, // Reemplazar (Beige)
					MEDIDAPIEZA, true, fechaRegistro, USERSYSTEM, "0.57 m × 0.49 m × 0.83 m", "Plástico con estructura metálica",
					null, null);

			Articulo sillaAvantGarden = new Articulo(null, "Silla AvantGarden",
					"Sillas plegables, livianas y se adaptan para cualquier tipo de eventos, bodas, bautizos, cumpleaños, comidas empresariales, etc.",
					tipoArticuloMobiliario, categoriaSilla, beige, // Blanco
					MEDIDAPIEZA, true, fechaRegistro, USERSYSTEM, "0.76 m × 0.39 m", "Resina de alta resistencia", null, null);

			Articulo mesaRedonda = new Articulo(null, "Mesa Redonda",
					"Mesa plegable redonda con excelente resistencia, adecuada para cualquier tipo de eventos, cumpleaños, bodas, reuniones empresariales, etc.",
					tipoArticuloMobiliario, categoriaMesa, blanco, // Blanco
					MEDIDAPIEZA, true, fechaRegistro, USERSYSTEM, "Diámetro 1.8 m", "Fibra de vidrio con estructura metálica", null,
					null);

			Articulo mesaRectangular = new Articulo(null, "Mesa Rectangular Plegable",
					"Mesa plegable rectangular con capacidad para 10 personas, ideal para todo tipo de eventos: cumpleaños, eventos empresariales, bodas, reuniones familiares, asados, etc.",
					tipoArticuloMobiliario, categoriaMesa, blanco, // Blanco
					MEDIDAPIEZA, true, fechaRegistro, USERSYSTEM, "2.4 m × 0.75 m", "Fibra con estructura metálica", "10 personas",
					null);

			
			Articulo calentadorExterior = new Articulo(null, "Calentador de Exteriores",
					"Calentador de exterior, brinda un ambiente cálido durante los meses más fríos. Funciona con gas propano. Cubre un área de 20 m2.",
					tipoArticuloEquipo, categoriaClimatizacion, aceroInoxidable, // Acero inoxidable
					MEDIDAPIEZA, true, fechaRegistro, USERSYSTEM, null, "Acero con acabado acero inoxidable", "20 m2", null);
			
			Articulo mantel = new Articulo(null, "Mantelería", "Mantelería estándar para eventos", tipoRopaMesa,
					categoriaProducto, colorSinDefinir, "Pieza", true, fechaRegistro, USERSYSTEM, "Varía según mesa", "Tela / Textil",
					null, "Incluye mantel para mesa rectangular o redonda");

			// 2. Loza
			Articulo loza = new Articulo(null, "Loza Completa", "Juego completo de loza para servicio", tipoLoza,
					categoriaProducto, colorSinDefinir, "Juego", true, fechaRegistro, USERSYSTEM, "Para 10 personas",
					"Cerámica / Porcelana", null, "Incluye platos y cubiertos según requerimiento");

			// 3. Cristalería
			Articulo cristaleria = new Articulo(null, "Cristalería", "Cristalería estándar para eventos", tipoCristaleria,
					categoriaProducto, colorSinDefinir, "Juego", true, fechaRegistro, USERSYSTEM, "Para 10 personas",
					"Vidrio templado", null, "Incluye vasos y copas según necesidad");
			
			log.info(ARTICULO, sillaLifetime);
			log.info(PRELOADING, articuloRepository.saveAndFlush(sillaLifetime));
			
			log.info(ARTICULO, sillaAvantGarden);
			log.info(PRELOADING, articuloRepository.saveAndFlush(sillaAvantGarden));
			
			log.info(ARTICULO, mesaRedonda);
			log.info(PRELOADING, articuloRepository.saveAndFlush(mesaRedonda));
			
			log.info(ARTICULO, mesaRectangular);
			log.info(PRELOADING, articuloRepository.saveAndFlush(mesaRectangular));
			
			log.info(ARTICULO, calentadorExterior);
			log.info(PRELOADING, articuloRepository.saveAndFlush(calentadorExterior));
			
			log.info(ARTICULO, mantel);
			log.info(PRELOADING, articuloRepository.saveAndFlush(mantel));
			
			log.info(ARTICULO, loza);
			log.info(PRELOADING, articuloRepository.saveAndFlush(loza));
			
			log.info(ARTICULO, cristaleria);
			log.info(PRELOADING, articuloRepository.saveAndFlush(cristaleria));
			
			
			log.info("");
			log.info("################   initDataBaseArticulo #########################################################################################");
			log.info("");
			log.info("");
			log.info("################   initDataBaseServicio #########################################################################################");
			log.info("");
			// ====== SERVICIO: Flete ======
			Servicio servicioFlete = new Servicio(null, // idServicio (autogenerado)
					"S-FLETE-001", // codigoServicio
					"Servicio de Flete", // nombreServicio
					"Servicio de transporte y entrega del mobiliario al evento.", TipoServicio.FLETE, // Tipo de servicio
					null, // costo (se calcula)
					true, // activo
					300.00, // tarifaBase estimada
					categoriaServicio, // categoría
					fechaRegistro, // fechaRegistro
					USERSYSTEM, // creadoPor
					null, // fechaModificacion
					null // modificadoPor
			);
			
			log.info(SERVICIO2, servicioFlete);
			log.info(PRELOADING, servicioRepository.saveAndFlush(servicioFlete));


			Categoria categoria2 = new Categoria(2, "Servicios de buffet", "Describe categoria", TipoCategoria.SERVICIO);
			log.info(PRELOADING, categoriaRepository.saveAndFlush(categoria2));
			log.info(CATEGORIA, categoria2);
			Servicio servicio = new Servicio(null, "SrvBuffet", "Catering", "Desayuno intercontinental",
					TipoServicio.BANQUETE, 5678.90, true, 5555d, categoria2, new Date(), "Por miguelito", null, null);
			log.info(SERVICIO2, servicio);
			log.info(PRELOADING, servicioRepository.saveAndFlush(servicio));

			log.info("");
			log.info("################   initDataBaseServicio #########################################################################################");
			log.info("");

			log.info("");
			log.info("################   initDataBasePaquete  #########################################################################################");
			log.info("");

			// ===== Datos generales =====
			boolean activo = true;

			// ===== Paquete 1 =====
			Paquete paquete1 = new Paquete(null, "Paquete 1",
					"Incluye sillas Avant Garden, mesa redonda o rectangular y mantelería.", 425.00, activo, "PQT-001",
					fechaRegistro, USERSYSTEM);

			// ===== Paquete 2 =====
			Paquete paquete2 = new Paquete(null, "Paquete 2", "Incluye 1 mantel, 10 sillas Lifetime y 1 mesa.", 380.00,
					activo, "PQT-002", fechaRegistro, USERSYSTEM);

			// ===== Paquete 3 =====
			Paquete paquete3 = new Paquete(null, "Paquete 3",
					"Mobiliario y loza para 10 personas: incluye sillas Avant Garden, mesa, mantelería, loza completa y cristalería.",
					1080.00, activo, "PQT-003", fechaRegistro, USERSYSTEM);

			log.info(SERVICIO2, paquete1);
			log.info(PRELOADING, paqueteRepository.saveAndFlush(paquete1));
			
			log.info(SERVICIO2, paquete2);
			log.info(PRELOADING, paqueteRepository.saveAndFlush(paquete2));
			
			log.info(SERVICIO2, paquete3);
			log.info(PRELOADING, paqueteRepository.saveAndFlush(paquete3));
			
			DetallePaqueteArticulo detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete1, sillaAvantGarden, 10, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete1, mesaRectangular, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete1, mantel, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			DetallePaqueteServicio detallePaqueteServicio = new DetallePaqueteServicio(null, paquete1, servicioFlete, 1, 1d);
			log.info(DETALLE_SERVICIO_EN_PAQUETE, detallePaqueteServicio);
			log.info(PRELOADING, detallePaqueteServicioRepository.saveAndFlush(detallePaqueteServicio));
			
			
			//Paquete 2
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete2, sillaLifetime, 10, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete2, mesaRectangular, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete2, mantel, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteServicio = new DetallePaqueteServicio(null, paquete2, servicioFlete, 1, 1d);
			log.info(DETALLE_SERVICIO_EN_PAQUETE, detallePaqueteServicio);
			log.info(PRELOADING, detallePaqueteServicioRepository.saveAndFlush(detallePaqueteServicio));

			//Paquete 3
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete3, sillaLifetime, 10, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete3, mesaRectangular, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete3, mantel, 1, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete3, loza, 10, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteArticulo = new DetallePaqueteArticulo(null, paquete3, cristaleria, 10, 1d);
			log.info(DETALLE_ARTICULO_EN_PAQUETE, detallePaqueteArticulo);
			log.info(PRELOADING, detallePaqueteArticuloRepository.saveAndFlush(detallePaqueteArticulo));
			
			detallePaqueteServicio = new DetallePaqueteServicio(null, paquete3, servicioFlete, 1, 1d);
			log.info(DETALLE_SERVICIO_EN_PAQUETE, detallePaqueteServicio);
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
