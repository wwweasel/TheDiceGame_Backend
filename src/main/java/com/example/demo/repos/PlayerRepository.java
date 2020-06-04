package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.PlayerDTO;
import com.example.demo.entities.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	

	
	
	@Query(value = "SELECT p.*, sub_1.succeded *100 / sub_2.success_all AS successrate "
					+"FROM player p "
					+"JOIN (SELECT p.id, COUNT(g.success) AS succeded "
							+"FROM player p "
							+"JOIN game g ON g.player_id = p.id "
							+"WHERE g.success=1 "
							+"GROUP BY p.id) sub_1 "
					+"ON sub_1.id = p.id "
					+"JOIN (SELECT p.id, COUNT(g.success) AS success_all "
							+"FROM player p "
							+"JOIN game g ON g.player_id = p.id "
							+"GROUP BY p.id) sub_2 "
					+"ON sub_2.id = p.id", nativeQuery=true)
	List<PlayerDTO> findAllDTO();
	
}
