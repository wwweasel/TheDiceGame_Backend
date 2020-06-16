package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="known_player")
@PrimaryKeyJoinColumn(name = "id")
public class KnownPlayer extends Player{
	
	@Column(name="name", unique=true, nullable = false)
	private String name;
	
	public KnownPlayer() {}
	public KnownPlayer(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
