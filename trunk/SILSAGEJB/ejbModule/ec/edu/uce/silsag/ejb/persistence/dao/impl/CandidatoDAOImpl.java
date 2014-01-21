package ec.edu.uce.silsag.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.edu.uce.silsag.commons.util.SilsagException;
import ec.edu.uce.silsag.ejb.persistence.dao.CandidatoDAO;
import ec.edu.uce.silsag.ejb.persistence.entities.CandidatoDTO;
import ec.edu.uce.silsag.ejb.persistence.entities.CandidatoEstudioDTO;

@Stateless
public class CandidatoDAOImpl extends AbstractFacadeImpl<CandidatoDTO> implements CandidatoDAO{
	
	public CandidatoDAOImpl () {}
	
	public CandidatoDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	
	
	@Override
	public List<CandidatoDTO> getAll() throws SilsagException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidatoDTO> cq=cb.createQuery(CandidatoDTO.class);
		cq.from(CandidatoDTO.class);
				
		List<CandidatoDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	
	@Override
	public Boolean getByIdentificacion(CandidatoDTO candidatoDTO) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidatoDTO> cq=cb.createQuery(CandidatoDTO.class);
		Root<CandidatoDTO> from= cq.from(CandidatoDTO.class);
		
		cq.where(cb.equal(from.get("canIdentificacion"), candidatoDTO.getCanIdentificacion()));
		
		List<CandidatoDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return false;
		else
			return true;	
	}
	
	
//	@Override
//	public List<CandidatoDatoDTO> getData(CandidatoDTO can) throws SilsagException
//	{
//		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//		CriteriaQuery<CandidatoDatoDTO> cq=cb.createQuery(CandidatoDatoDTO.class);
//		Root<CandidatoDatoDTO> from =cq.from(CandidatoDatoDTO.class);
//		
//		cq.where(cb.equal(from.get("canCodigo"), can.getCanCodigo()));
//				
//		List<CandidatoDatoDTO> list=entityManager.createQuery(cq).getResultList();
//		
//		if(list.isEmpty())
//			return null;
//		else
//			return list;
//	}
	

	@Override
	public List<CandidatoEstudioDTO> getCandidatoEstudio() throws SilsagException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidatoEstudioDTO> cq=cb.createQuery(CandidatoEstudioDTO.class);
		cq.from(CandidatoEstudioDTO.class);
				
		List<CandidatoEstudioDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	
//	@Override
//	public List<CandidatoEstudioDTO> getCandidatoEstudio(CandidatoDTO candidatoDTO) throws SilsagException
//	{
//		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//		CriteriaQuery<CandidatoEstudioDTO> cq=cb.createQuery(CandidatoEstudioDTO.class);
//		Root<CandidatoEstudioDTO> from= cq.from(CandidatoEstudioDTO.class);
//		
//		cq.where(cb.equal(from.get("canCodigo"),candidatoDTO.getCanCodigo()));
//		
//		List<CandidatoEstudioDTO> list=entityManager.createQuery(cq).getResultList();
//		if(list.isEmpty())
//			return null;
//		else
//			return list;
//	}

}
