package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="user")
public class SampleUser implements Serializable{
	private static final long serialVersionUID = 1L;

	// Database table: 
	// user_id → PK
	// user_name
	// user_age
	// user_zodiac
	
	@Id
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="user_age")
	private String age;
	
	@Column(name="user_zodiac")
	private String zodiac;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
}
