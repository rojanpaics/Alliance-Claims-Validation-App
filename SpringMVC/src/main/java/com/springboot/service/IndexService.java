package com.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Index;
import com.springboot.repository.custom.IndexRepository;

@Service
public class IndexService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IndexRepository indexRepository;
	
	public void insertSampleLoad(String message) {
		Index indexEntity = new Index();
		indexEntity.setMessage(message);
//		indexRepository.insertSimpleValue(em, "String");
		try {
			indexRepository.insertSimpleValueWithEntity(em, indexEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateSampleLoad(int id, String message) {
		Index indexEntity = new Index();
		indexEntity.setIndexId((long) id);
		indexEntity.setMessage(message);
//		indexRepository.insertSimpleValue(em, "String");
		try {
			indexRepository.updateSimpleValueWithEntity(em, indexEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
