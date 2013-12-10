package ec.edu.uce.silsae.ejb.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.silsae.commons.util.SilsaeException;
import ec.edu.uce.silsae.ejb.negocio.CandidatosService;
import ec.edu.uce.silsae.ejb.persistence.dao.FactoryDAO;
import ec.edu.uce.silsae.ejb.persistence.entities.AvisoListDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.CandidatoDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.EstudioDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.EstudioListDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.ExperienciaDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.ExperienciaListDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.PostulacionDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.UsuarioDTO;

@Stateless
public class CandidatosServiceImpl implements CandidatosService {

	private static final Logger log = LoggerFactory
			.getLogger(CandidatosServiceImpl.class);

	@EJB
	private FactoryDAO factoryDAO;

	@Override
	public CandidatoDTO registrarCandidato(CandidatoDTO candidatoDTO)throws SilsaeException {

		log.info("registrarCandidato");

		try {

			UsuarioDTO user = new UsuarioDTO();
			user.setBemPerfil(factoryDAO.getPerfilDAOImpl().find(1));
			return factoryDAO.getCandidatoDAOImpl().create(candidatoDTO);
		} catch (Exception e) {
			log.info("Error al registrar el Candidato {}", e.toString());
			throw new SilsaeException("Error al registrar el Candidato");
		}

	}

	@Override
	public void agregarEstudio(EstudioDTO estudio) throws SilsaeException {
		log.info("agregarEstudio");

		try {
			factoryDAO.getEstudioDAOImpl().create(estudio);
		} catch (Exception e) {
			log.info("Error al registrar el Candidato {}", e.toString());
			throw new SilsaeException("Error al registrar el Candidato");
		}
	}
	
	@Override
	public void agregarExperiencia(ExperienciaDTO experiencia) throws SilsaeException {
		log.info("agregarEstudio");

		try {
			factoryDAO.getExperienciaDAOImpl().create(experiencia);
		} catch (Exception e) {
			log.info("Error al registrar el Candidato {}", e.toString());
			throw new SilsaeException("Error al registrar el Candidato");
		}
	}
	

	@Override
	public List<EstudioListDTO> obtenerEstudio(CandidatoDTO candidato)throws SilsaeException {
		try {
			return factoryDAO.getEstudioDAOImpl().getAll(candidato);
		} catch (Exception e) {
			log.info("Error al obtener datos de Estudio {}", e.toString());
			throw new SilsaeException("Error al registrar el Candidato");

		}
	}

	@Override
	public List<ExperienciaListDTO> obtenerExperiencia(CandidatoDTO candidato)throws SilsaeException {
		try {
			return factoryDAO.getExperienciaDAOImpl().getAll(candidato);
		} catch (Exception e) {
			log.info("Error al obtener datos de Estudio {}", e.toString());
			throw new SilsaeException("Error al registrar el Candidato");

		}
	}

	@Override
	public List<AvisoListDTO> verOfertas() throws SilsaeException
	{
		try {
			return factoryDAO.getAvisoDAOImpl().getOfertas();
		} catch (Exception e) {
			log.info("Error al obtener datos de Estudio {}", e.toString());
			throw new SilsaeException("Error al obtener datos Ofertas");

		}
	}
	
	@Override
	public PostulacionDTO postular(PostulacionDTO postulacionDTO) throws SilsaeException{
		try {
			return factoryDAO.getPostulacionDAOImpl().create(postulacionDTO);
		} catch (Exception e) {
			log.info("Error al insertar postulacion {}", e.toString());
			throw new SilsaeException("Error al insertar postulacion");
		}
		
	}
}
