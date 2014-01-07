package ec.edu.uce.indicadores.ejb.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.indicadores.commons.util.IndicadoresException;
import ec.edu.uce.indicadores.ejb.negocio.IndicadorService;
import ec.edu.uce.indicadores.ejb.persistence.dao.FactoryDAO;
import ec.edu.uce.indicadores.ejb.persistence.entities.ContactoDTO;
import ec.edu.uce.indicadores.ejb.persistence.entities.IesDTO;
import ec.edu.uce.indicadores.ejb.persistence.entities.IndicadorDTO;
import ec.edu.uce.indicadores.ejb.persistence.entities.ModeloDTO;
import ec.edu.uce.indicadores.ejb.persistence.entities.RepresentanteLegalDTO;

@Stateless
public class IndicadorServiceImpl implements IndicadorService {

	@EJB
	private FactoryDAO factoryDAO;
	
	private static final Logger log = LoggerFactory
			.getLogger(IndicadorServiceImpl.class);

	
	
	@Override
	public void agregarRepresentanteLegal(RepresentanteLegalDTO representanteLegalDTO) throws IndicadoresException
	{
		log.info("agregarRepresentanteLegal");
		try {
			factoryDAO.getRepresentanteLegalDAOImpl().create(representanteLegalDTO);
		} catch (Exception e) {
			log.error(e.toString());
			throw new IndicadoresException(e);
		}
	}

	@Override
	public void agregarContacto(ContactoDTO contactoDTO) throws IndicadoresException
	{
		log.info("agregarContacto");
		try {
			factoryDAO.getContactoDAOImpl().create(contactoDTO);
		} catch (Exception e) {
			log.error(e.toString());
			throw new IndicadoresException(e);
		}
	}

	@Override
	public void agregarIes(IesDTO iesDTO) throws IndicadoresException
	{
		log.info("agregarIes");
		try {
			factoryDAO.getIesDAOImpl().create(iesDTO);
		} catch (Exception e) {
			log.error(e.toString());
			throw new IndicadoresException(e);
		}
	}

	@Override
	public void agregarIndicador(IndicadorDTO indicadorDTO) throws IndicadoresException
	{
		log.info("agregarIndicador");
		try {
			factoryDAO.getIndicadorDAOImpl().create(indicadorDTO);
		} catch (Exception e) {
			log.error(e.toString());
			throw new IndicadoresException(e);
		}
	}

	
	@Override
	public List<RepresentanteLegalDTO> obtenerRepresentantes() throws IndicadoresException
	{
		try {
			return factoryDAO.getRepresentanteLegalDAOImpl().getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}
	
	@Override
	public List<ContactoDTO> obtenerContactos(RepresentanteLegalDTO representanteLegalDTO) throws IndicadoresException
	{
		try {
			return factoryDAO.getContactoDAOImpl().getAll(representanteLegalDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}
	
	@Override
	public List<IesDTO> obtenerIes() throws IndicadoresException
	{
		try {
			return factoryDAO.getIesDAOImpl().getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}

	@Override
	public List<ModeloDTO> obtenerModelo() throws IndicadoresException
	{
		try {
			return factoryDAO.getModeloDAOImpl().getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}
	
	@Override
	public List<IndicadorDTO> obtenerIndicador() throws IndicadoresException
	{
		try {
			return factoryDAO.getIndicadorDAOImpl().getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}
	
	@Override
	public List<IndicadorDTO> obtenerRaizIndicador(IndicadorDTO indicadorDTO) throws IndicadoresException
	{
		try {
			return factoryDAO.getIndicadorDAOImpl().getRoot(indicadorDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}

	@Override
	public List<IndicadorDTO> obtenerHijosIndicador(IndicadorDTO indicadorDTO) throws IndicadoresException
	{
		try {
			return factoryDAO.getIndicadorDAOImpl().getChildren(indicadorDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IndicadoresException(e);
		}
	}

}