package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
	
//	@Query("SELECT g FROM Game g WHERE g.player.id = :playerId")
//	List<Game> getGamesByPlayerId(Integer playerId);
	
	@Query("SELECT g FROM Game g WHERE g.player.id = :playerId")
	List<Game> getGamesByPlayerId(Integer playerId);
	
	@Query(value = "SELECT COUNT(g.success) * 100.0 / ( SELECT COUNT(g.success) FROM game g WHERE g.player_id = :playerId )FROM game g WHERE g.player_id = :playerId and g.success = true", nativeQuery=true)
	double calculateSuccessRate(Integer playerId);
	
}
