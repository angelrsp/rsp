package ec.edu.uce.besg.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.besg.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.besg.ejb.persistence.dao.CandidatoDAO;
import ec.edu.uce.besg.ejb.persistence.dao.CatalogoDAO;
import ec.edu.uce.besg.ejb.persistence.dao.ContactoDAO;
import ec.edu.uce.besg.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.besg.ejb.persistence.dao.ExperienciaDAO;
import ec.edu.uce.besg.ejb.persistence.dao.HabilidadDAO;
import ec.edu.uce.besg.ejb.persistence.dao.HabilidadViewDAO;
import ec.edu.uce.besg.ejb.persistence.dao.HistorialPasswordDAO;
import ec.edu.uce.besg.ejb.persistence.dao.ReferenciaDAO;
import ec.edu.uce.besg.ejb.persistence.dao.UsuarioDAO;
import ec.edu.uce.besg.ejb.persistence.dao.impl.CandidatoDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.CatalogoDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.ContactoDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.EmpresaDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.ExperienciaDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.HabilidadDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.HabilidadViewDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.HistorialPasswordDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.ReferenciaDAOImpl;
import ec.edu.uce.besg.ejb.persistence.dao.impl.UsuarioDAOImpl;

@Stateless
public class FactoryDAOImpl implements FactoryDAO {

	@PersistenceContext(unitName = "besgPU")
	private EntityManager entityManager;

	private UsuarioDAO usuarioDAO;
	private CatalogoDAO catalogoDAO;
	private CandidatoDAO candidatoDAO;
	private HabilidadDAO habilidadDAO;
	private HabilidadViewDAO habilidadViewDAO;
	private ExperienciaDAO experienciaDAO;
	private ReferenciaDAO referenciaDAO;

	private ContactoDAO contactoDAO;
	private EmpresaDAO empresaDAO;
	
	private HistorialPasswordDAO historialPasswordDAO;
	
	@Override
	public UsuarioDAO getUsuarioDAOImpl() {
		if (usuarioDAO == null) {
			usuarioDAO = new UsuarioDAOImpl(entityManager);
		}
		return usuarioDAO;
	}

	@Override
	public CandidatoDAO getCandidatoDAOImpl() {
		if (candidatoDAO == null) {
			candidatoDAO = new CandidatoDAOImpl(entityManager);
		}
		return candidatoDAO;
	}
	
	@Override
	public CatalogoDAO getCatalogoDAOImpl() {
		if (catalogoDAO == null) {
			catalogoDAO = new CatalogoDAOImpl(entityManager);
		}
		return catalogoDAO;
	}

	@Override
	public HabilidadDAO getHabilidadDAOImpl() {
		if (habilidadDAO == null) {
			habilidadDAO = new HabilidadDAOImpl(entityManager);
		}
		return habilidadDAO;
	}

	@Override
	public ExperienciaDAO getExperienciaDAOImpl() {
		if (experienciaDAO == null) {
			experienciaDAO = new ExperienciaDAOImpl(entityManager);
		}
		return experienciaDAO;
	}

	@Override
	public ReferenciaDAO getReferenciaDAOImpl() {
		if (referenciaDAO == null) {
			referenciaDAO = new ReferenciaDAOImpl(entityManager);
		}
		return referenciaDAO;
	}
	

	@Override
	public EmpresaDAO getEmpresaDAOImpl() {
		if (empresaDAO == null) {
			empresaDAO = new EmpresaDAOImpl(entityManager);
		}
		return empresaDAO;
	}

	@Override
	public ContactoDAO getContactoDAOImpl() {
		if (contactoDAO == null) {
			contactoDAO = new ContactoDAOImpl(entityManager);
		}
		return contactoDAO;
	}

	@Override
	public HistorialPasswordDAO getHistorialPasswordDAOImpl() {
		if (historialPasswordDAO == null) {
			historialPasswordDAO = new HistorialPasswordDAOImpl(entityManager);
		}
		return historialPasswordDAO;
	}


	@Override
	public HabilidadViewDAO getHabilidadViewDAOImpl() {
		if (habilidadViewDAO == null) {
			habilidadViewDAO = new HabilidadViewDAOImpl(entityManager);
		}
		return habilidadViewDAO;
	}

	
}
