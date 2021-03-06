package ec.edu.uce.indicadores.ejb.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ec.edu.uce.indicadores.ejb.persistence.dao.AbstractFacade;

public abstract class AbstractFacadeImpl<T> implements AbstractFacade<T>{
	
//	@PersistenceContext(unitName = "silsaePU")
	protected EntityManager entityManager;

	private Class<T> entityClass;
	
	public AbstractFacadeImpl(){}

	@SuppressWarnings("unchecked")
	public AbstractFacadeImpl(EntityManager entityManager) {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.entityManager = entityManager;
	}
	
//	protected EntityManager getEntityManager(){
//		return this.entityManager;
//	}

	public T create(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public T edit(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public T find(Object id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq=cb.createQuery(entityClass);
		cq.from(entityClass);
		
		List<T> list=entityManager.createQuery(cq).getResultList();
		
		if(list.isEmpty())
			return new ArrayList<T>();
		else
			return list;
	}
	
//	public List<T> findAll() {
//		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
//				.getCriteriaBuilder().createQuery();
//		cq.select(cq.from(entityClass));
//		return getEntityManager().createQuery(cq).getResultList();
//	}
//
//	public List<T> findRange(int[] range) {
//		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
//				.getCriteriaBuilder().createQuery();
//		cq.select(cq.from(entityClass));
//		javax.persistence.Query q = getEntityManager().createQuery(cq);
//		q.setMaxResults(range[1] - range[0]);
//		q.setFirstResult(range[0]);
//		return q.getResultList();
//	}
//
//	public int count() {
//		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
//				.getCriteriaBuilder().createQuery();
//		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//		javax.persistence.Query q = getEntityManager().createQuery(cq);
//		return ((Long) q.getSingleResult()).intValue();
//	}

}
