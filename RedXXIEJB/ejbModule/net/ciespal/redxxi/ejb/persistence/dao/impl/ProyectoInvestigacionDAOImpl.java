package net.ciespal.redxxi.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.ciespal.redxxi.ejb.persistence.dao.ProyectoInvestigacionDAO;
import net.ciespal.redxxi.ejb.persistence.entities.CarreraDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EntidadDTO;
import net.ciespal.redxxi.ejb.persistence.entities.OrganizacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ProyectoInvestigacionDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

public class ProyectoInvestigacionDAOImpl extends AbstractFacadeImpl<ProyectoInvestigacionDTO> implements ProyectoInvestigacionDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(ProyectoInvestigacionDAOImpl.class);
	
	public ProyectoInvestigacionDAOImpl() {
		super();
	}

	public ProyectoInvestigacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ProyectoInvestigacionDTO> getAll(CarreraDTO carrera) throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ProyectoInvestigacionDTO> cq=cb.createQuery(ProyectoInvestigacionDTO.class);
		Root<ProyectoInvestigacionDTO> from = cq.from(ProyectoInvestigacionDTO.class);
		
		Path<CarreraDTO> join1=from.join("ateEntidads");
		
		cq.where(cb.equal(join1.get("ateCarrera"), carrera.getCarCodigo()));
		
		List<ProyectoInvestigacionDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	
	@Override
	public List<ProyectoInvestigacionDTO> getAll(OrganizacionDTO org) throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ProyectoInvestigacionDTO> cq=cb.createQuery(ProyectoInvestigacionDTO.class);
		Root<ProyectoInvestigacionDTO> from = cq.from(ProyectoInvestigacionDTO.class);
		
		Path<OrganizacionDTO> join1=from.join("ateEntidads");
		
		cq.where(cb.equal(join1.get("ateOrganizacion"), org.getOrgCodigo()));
		
		List<ProyectoInvestigacionDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public List<ProyectoInvestigacionDTO> getAll() throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ProyectoInvestigacionDTO> cq=cb.createQuery(ProyectoInvestigacionDTO.class);
		cq.from(ProyectoInvestigacionDTO.class);
		
		List<ProyectoInvestigacionDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return new ArrayList<ProyectoInvestigacionDTO>();
		else
			return list;
	}
		
	@Override
	public Integer getCount(Object pais) throws CorvustecException
	{		
		CriteriaBuilder cb;
		CriteriaQuery<ProyectoInvestigacionDTO> cq;
		Root<ProyectoInvestigacionDTO> from;
		List<ProyectoInvestigacionDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		try{
		
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(ProyectoInvestigacionDTO.class);
			
			from= cq.from(ProyectoInvestigacionDTO.class);
			
			cq.multiselect(cb.count(from.get("pinCodigo")));
			
			predicateList=new ArrayList<Predicate>();
			if(pais!=null)
			{
				predicate=cb.equal(from.get("pinPais"),pais);
				predicateList.add(predicate);
			}
			
			cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<ProyectoInvestigacionDTO> tq=entityManager.createQuery(cq);
			
			list=tq.getResultList();
			
			if(!list.isEmpty())
				return (int)(long)list.get(0).getPinCount();
			else
				return 0;
			
		}catch(Exception e){
			logger.info(e.toString());
			throw new CorvustecException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}
	}

	
	@Override
	public List<ProyectoInvestigacionDTO> getAll(Object pais) throws CorvustecException
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ProyectoInvestigacionDTO> cq=cb.createQuery(ProyectoInvestigacionDTO.class);
		Root<ProyectoInvestigacionDTO> from= cq.from(ProyectoInvestigacionDTO.class);
		
		cq.where(cb.equal(from.get("pinPais"), pais));
		
		List<ProyectoInvestigacionDTO> list=entityManager.createQuery(cq).getResultList();
		if(list.isEmpty())
			return new ArrayList<ProyectoInvestigacionDTO>();
		else
			return list;
	}

	
	@Override
	public void remove2(ProyectoInvestigacionDTO pro)
	{
		Query query;
		for(EntidadDTO ent:pro.getAteEntidads())
		{	 
			query= entityManager.createQuery("delete from EntidadDTO where entCodigo=:codigo");
			query.setParameter("codigo", ent.getEntCodigo());
			query.executeUpdate();
		}
		
		query= entityManager.createQuery("delete from ProyectoInvestigacionDTO where pinCodigo=:codigo");
		query.setParameter("codigo", pro.getPinCodigo());
		query.executeUpdate();
	}
}
