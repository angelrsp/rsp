package net.ciespal.redxxi.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.ciespal.redxxi.ejb.persistence.dao.OrganizacionVieDAO;
import net.ciespal.redxxi.ejb.persistence.entities.OrganizacionVieDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

public class OrganizacionVieDAOImpl extends AbstractFacadeImpl<OrganizacionVieDTO> implements OrganizacionVieDAO{

	private static final Logger logger = LoggerFactory.getLogger(OrganizacionVieDAOImpl.class);
	
	public OrganizacionVieDAOImpl() {
		super();
	}

	public OrganizacionVieDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	@Override
	public List<OrganizacionVieDTO> getByAnd(OrganizacionVieDTO doctorVieDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
		CriteriaQuery<OrganizacionVieDTO> cq;
		Root<OrganizacionVieDTO> from;
		List<OrganizacionVieDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(OrganizacionVieDTO.class);
			
			from= cq.from(OrganizacionVieDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = doctorVieDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = doctorVieDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(doctorVieDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<OrganizacionVieDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			logger.info(e.toString());
			throw new CorvustecException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}		
	}
		
}
