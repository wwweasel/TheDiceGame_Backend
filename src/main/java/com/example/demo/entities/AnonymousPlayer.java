package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="anonymous_player")
@PrimaryKeyJoinColumn(name = "id")
public class AnonymousPlayer extends Player{
	
	@Column(name="name", columnDefinition="varchar(30) default 'Anonymous'", nullable = false)
	private String name = "Anonymous";
	
	
	public AnonymousPlayer() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
