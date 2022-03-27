package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="indextable")
public class Index implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="index_id")
	private long index_id;
	
	@Column(name="index_message")
	private String message;

	public long getIndex_id() {
		return index_id;
	}

	public void setIndexId(long index_id) {
		this.index_id = index_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
