package com.springboot.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entities.SampleUser;

@Repository
@Transactional
public class SampleRepository {
	
	// CREATE
	public void insertSampleUserToRepository(EntityManager em, SampleUser user) {
		try {
			em.persist(user);
			em.flush();
			em.detach(user);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	// RETRIEVE OR READ ONE
	public SampleUser getSampleUserFromRepository(EntityManager em, int userId) {
		SampleUser userFromDb = null;
		
		try {
			userFromDb = em.find(SampleUser.class, userId);
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		return userFromDb;
	}
	
	// RETRIEVE MANY OR ALL
	public List<SampleUser> getSampleUserListFromRepository(EntityManager em, String zodiac) {
		List<SampleUser> usersList = new ArrayList<>();
		
		try {
			StringBuilder query = new StringBuilder("FROM user WHERE user_zodiac = " + zodiac);
			Query q = em.createQuery(query.toString());
			usersList = q.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		return usersList;
	}
	
	// UPDATE
	public void updateSampleUserToRepository(EntityManager em, SampleUser user) {
		try {
			// em.find → SELECT * FROM user WHERE user_id = ? LIMIT 1
			SampleUser userFromDb = em.find(SampleUser.class, user.getId());
			if(null != userFromDb) {
				em.merge(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	// DELETE
	public void deleteSampleUserFromRepository(EntityManager em, int userId) {
		try {
			SampleUser userFromDb = em.find(SampleUser.class, userId);
			if(null != userFromDb) {
				em.remove(userFromDb);
				em.flush();
				em.clear();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
