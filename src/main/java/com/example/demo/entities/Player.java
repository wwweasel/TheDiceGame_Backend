package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="player")
public class Player {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games = new ArrayList<Game>();
	
	@Column(name = "registration_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime registrationDateTime;

	@Column(name="name", columnDefinition="varchar(30) default 'Anonymous'", unique=true)
	private String name = null;
	
	// Constructors
	public Player() {}

	// special methods
	@PrePersist
	public void prePersist(){
		this.registrationDateTime = LocalDateTime.now();
	}

	// getters and setters
	public LocalDateTime getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	
	public List<Game> getGames() {
		return this.games;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
