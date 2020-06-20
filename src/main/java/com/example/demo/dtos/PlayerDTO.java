package com.example.demo.dtos;

import java.time.LocalDateTime;

import com.example.demo.tools.View;
import com.fasterxml.jackson.annotation.JsonView;

public class PlayerDTO {

	@JsonView({View.Short.class, View.All.class})
	private Integer id = null;
	@JsonView({View.Short.class, View.All.class})
	private String name;
	@JsonView({View.All.class})
	private LocalDateTime registrationDateTime = null;

	// DTO specific fields
	@JsonView({View.Short.class, View.All.class})
	private String successrate="not available";
	
	
	// Constructors
	public PlayerDTO() {}
	public PlayerDTO(Integer id, String name, LocalDateTime registrationDateTime, String successrate, boolean anonymous) {
		this.id = id;
		this.name = name;
		this.registrationDateTime = registrationDateTime;
		this.successrate = successrate;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	
	public String getSuccessrate() {
		return successrate;
	}
	public void setSuccessrate(String successrate) {
		this.successrate = successrate;
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
		PlayerDTO other = (PlayerDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
