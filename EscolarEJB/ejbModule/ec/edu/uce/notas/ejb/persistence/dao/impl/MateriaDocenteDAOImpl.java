package ec.edu.uce.notas.ejb.persistence.dao.impl;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.notas.common.util.CorvustecException;

import ec.edu.uce.notas.ejb.persistence.dao.MateriaDocenteDAO;
import ec.edu.uce.notas.ejb.persistence.entity.MateriaDocenteDTO;

public class MateriaDocenteDAOImpl extends AbstractFacadeImpl<MateriaDocenteDTO> implements MateriaDocenteDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(MateriaDocenteDAOImpl.class);
	
	public MateriaDocenteDAOImpl() {
		super();
	}

	public MateriaDocenteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<MateriaDocenteDTO> getByAnd(MateriaDocenteDTO objectDTO) throws CorvustecException
	{
		CriteriaBuilder cb;
        CriteriaQuery<MateriaDocenteDTO> cq;
        Root<MateriaDocenteDTO> from;
        List<MateriaDocenteDTO> list;
        Predicate predicate;
        List<Predicate> predicateList = null;
        String fieldName;
        Method getter;
        Object value;
        Field[] fields;
        TypedQuery<MateriaDocenteDTO> typedQuery;
        try{
            cb=entityManager.getCriteriaBuilder();
            cq=cb.createQuery(MateriaDocenteDTO.class);
           
            from= cq.from(MateriaDocenteDTO.class);
           
            predicateList=new ArrayList<Predicate>();
           
            fields = objectDTO.getClass().getDeclaredFields();

            for(Field f : fields){
                fieldName = f.getName();
                if(!fieldName.equals("serialVersionUID"))
                {
                    getter = objectDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
                            fieldName.substring(1));
                   
                    value = getter.invoke(objectDTO, new Object[0]);
               
                    if(value!=null)
                    {
                        predicate=cb.equal(from.get(fieldName), value);
                        predicateList.add(predicate);                   
                    }
                }
            }
   
            if(!predicateList.isEmpty())
                cq.where(cb.and(predicateList.toArray(new Predicate[0])));       
           
            typedQuery=entityManager.createQuery(cq);
            list=typedQuery.getResultList();
           
            return list;
           
        }catch(Exception e){
            slf4jLogger.info(e.toString());
            throw new CorvustecException(e);
        }finally{
        	 predicate=null;
             predicateList=null;
             cb=null;
             cq=null;
             typedQuery=null;
             from=null;
             fieldName=null;
             getter=null;
             value=null;
             fields=null;
        }       
	}
	
}
