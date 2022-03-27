package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Index;

@Repository
@Transactional
public class IndexRepository {
	
	public void insertSimpleValue(EntityManager em, String value) {
		
	}
	
	public void insertSimpleValueWithEntity(EntityManager em, Index indexEntity) throws Exception {
		try {
			em.persist(indexEntity);
			em.flush();
			em.detach(indexEntity);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public List<Index> getSimpleValueWithEntity(EntityManager em) throws Exception {
		try {
			StringBuilder queryString = new StringBuilder("From indextable WHERE index_id > 3");
			Query q = em.createQuery(queryString.toString());
			List<Index> indexList = null;
			indexList = q.getResultList();
			return indexList;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void updateSimpleValueWithEntity(EntityManager em, Index indexEntity) throws Exception {
		try {
			em.merge(indexEntity);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteSimpleValueWithEntity(EntityManager em, long id) throws Exception {
		try {
			Index indexEntity = new Index();
			indexEntity = em.find(Index.class, id);
			em.remove(indexEntity);
			em.flush();
			em.clear();
		} catch(Exception e) {
			throw e;
		}
	}
}
