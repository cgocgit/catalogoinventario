package mx.com.mesaregia.catalogoinventario.service;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import mx.com.mesaregia.catalogoinventario.application.catalogo.ArticuloService;
import mx.com.mesaregia.catalogoinventario.application.inventario.AlmacenService;
import mx.com.mesaregia.catalogoinventario.application.inventario.ExistenciaArticuloBuilder;
import mx.com.mesaregia.catalogoinventario.application.inventario.ExistenciaArticuloService;
import mx.com.mesaregia.catalogoinventario.application.inventario.InventariadoService;
import mx.com.mesaregia.catalogoinventario.application.inventario.InventarioServiceImpl;
import mx.com.mesaregia.catalogoinventario.application.inventario.MovimientoInventarioService;
import mx.com.mesaregia.catalogoinventario.domain.Almacen;
import mx.com.mesaregia.catalogoinventario.domain.Articulo;
import mx.com.mesaregia.catalogoinventario.domain.ExistenciaArticulo;
import mx.com.mesaregia.catalogoinventario.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
class InventarioServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InventarioServiceTest.class);

	@Mock
	private InventarioRepository inventarioRepository;
	
	@Mock
	private ExistenciaArticuloService existenciaArticuloService;
	
	@Mock
	private ArticuloService articuloService;
	
	@Mock
	private AlmacenService almacenService;
	
	@Mock
	private InventariadoService inventariadoService;
	
	@Mock
	private MovimientoInventarioService movimientoInventarioService;

	@InjectMocks
	private InventarioServiceImpl inventarioService;
	
	
	@Mock
	private ExistenciaArticuloBuilder builder;


	@BeforeEach
	void setUp() {
	MockitoAnnotations.openMocks(this);
	}


	@Test
	void debeGuardarInventario() {
		
		ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
		existenciaArticulo.setCodigoUnidad("Codigo2");
		List<ExistenciaArticulo> lst = Arrays.asList(existenciaArticulo);	
		when(existenciaArticuloService.listar()).thenReturn(lst);
		
		Articulo articulo = new Articulo(1L,"ArticloTest", "", null, null, null, "", true, new java.util.Date(), "");
		when(articuloService.obtenerArticulo(anyInt())).thenReturn(articulo);
		
		
		Almacen almacen = new Almacen(1, "AlmacenTest", "", "", 0, null);
		when(almacenService.obtenerAlmacen(anyInt())).thenReturn(almacen);
		
		
		when(inventariadoService.obtener(anyInt(), anyInt())).thenReturn(null);
		
		doNothing().when(movimientoInventarioService).registraMovimiento(any());
		
		ExistenciaArticulo existenciaArticulo2 = new ExistenciaArticulo();
		existenciaArticulo2.setIdExistencia(1);
		existenciaArticulo2.setAlmacen(almacen);
		existenciaArticulo2.setArticulo(articulo);
		when(builder.get()).thenReturn(existenciaArticulo2);
		when(existenciaArticuloService.persistir(any())).thenReturn(existenciaArticulo2);
		
		
		ExistenciaArticulo resultado = inventarioService.agregarArticulo(1, 1, "codigo");
		LOGGER.info("Resultado obtenido: {}", resultado);
		assertNotNull(resultado);
		assertEquals(1, resultado.getIdExistencia());
	}
}
