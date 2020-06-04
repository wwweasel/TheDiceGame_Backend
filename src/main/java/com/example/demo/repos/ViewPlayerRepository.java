package com.example.demo.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.ViewPlayer;


@Repository
public interface ViewPlayerRepository extends JpaRepository<ViewPlayer, Integer>{
	
}
