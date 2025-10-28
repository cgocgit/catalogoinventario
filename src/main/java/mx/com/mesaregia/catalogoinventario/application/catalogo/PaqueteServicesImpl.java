package mx.com.mesaregia.catalogoinventario.application.catalogo;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.mesaregia.catalogoinventario.api.NotFoundException;
import mx.com.mesaregia.catalogoinventario.domain.Paquete;
import mx.com.mesaregia.catalogoinventario.repository.PaqueteRepository;

/**
 *
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0
 */
@Service
public class PaqueteServicesImpl implements PaqueteService {

	private static final Logger log = LoggerFactory.getLogger(PaqueteServicesImpl.class);
	
	private final PaqueteRepository paqueteRepository;

	/**
	 * 
	 */
	public PaqueteServicesImpl(PaqueteRepository paqueteRepository) {
		this.paqueteRepository = paqueteRepository;
	}

	@Override
	public void actualizarPaquete(Paquete paquete) throws NotFoundException {
		Paquete paqueteDelRepositorio = paqueteRepository.findById(paquete.getIdPaquete())
				.orElseThrow(() -> new NotFoundException(paquete.getIdPaquete().toString()));
		trasladarDatos(paquete, paqueteDelRepositorio);
		paqueteRepository.save(paqueteDelRepositorio);
	}

	/**
	 * @param paquete
	 * @param paqueteDelRepositorio
	 */
	private void trasladarDatos(Paquete paquete, Paquete paqueteDelRepositorio) {
		paqueteDelRepositorio.setCodigoPaquete(paquete.getCodigoPaquete());
		paqueteDelRepositorio.setDescripcion(paquete.getDescripcion());
		paqueteDelRepositorio.setNombrePaquete(paquete.getNombrePaquete());
		paqueteDelRepositorio.setPrecio(paquete.getPrecio());
	}

	@Override
	public void bajarPaquete(Integer idPaquete) throws NotFoundException {
		Paquete paquete = paqueteRepository.findById(idPaquete).get();
		if (Objects.isNull(paquete))
			throw new NotFoundException();
		paquete.setActivo(false);
		paqueteRepository.save(paquete);
	}

	@Override
	public Paquete obtenerPaquete(Integer idPaquete) throws NotFoundException {
		return paqueteRepository.findById(idPaquete).orElseThrow(() -> new NotFoundException(idPaquete.toString()));
	}

	@Override
	public Collection<Paquete> obtenerPaquetes() {
		return paqueteRepository.findAll();
	}

	@Override
	public Paquete registrarPaquete(Paquete paquete) {
		paquete.setActivo(true);
		paquete.setIdPaquete(null);
		paqueteRepository.save(paquete);
		log.info(paquete.toString());
		return paquete;
	}

}
