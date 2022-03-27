package com.springboot.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.SampleUser;
import com.springboot.repository.custom.SampleRepository;

@Service
public class SampleService {
	
	@Autowired
	private SampleRepository sampleRepository;
	
	@Autowired
	private EntityManager em;
	
	public String testSampleService(String name) {
		// goal: Add [TESTED] at the end of the name
		// Jose Mari Chan → Jose Mari Chan [TESTED]
		return name + " [TESTED]";
	}
	
	public void testInsertSampleUserService(String name, String age, String zodiac) {
		SampleUser sampleUser = new SampleUser();
		sampleUser.setName(name);
		sampleUser.setAge(age);
		sampleUser.setZodiac(zodiac);
		
		sampleRepository.insertSampleUserToRepository(em, sampleUser);
	}
	
}
