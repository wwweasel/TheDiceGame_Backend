package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.GameDTO;
import com.example.demo.dtos.PlayerDTO;
import com.example.demo.entities.Game;
import com.example.demo.repos.ViewPlayerRepository;
import com.example.demo.services.GameService;
import com.example.demo.services.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	@Autowired
	GameService gameService;
	
	@Autowired
	ViewPlayerRepository viewPlayerRepo;
	
	
	@RequestMapping(method=RequestMethod.POST,value={"/players/"})
	public PlayerDTO craetePlayer(@RequestBody PlayerDTO dto){
		return playerService.save(dto);
	}
	
	//////////////
	
	@RequestMapping(method=RequestMethod.POST,value={"/players/{playerId}/games/"})
	public GameDTO play(@PathVariable Integer playerId) {
		return gameService.play(playerId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value={"/players/{playerId}/games/"})
	public ResponseEntity<String> delete(@PathVariable Integer playerId) {
		gameService.delete(playerId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/players/{playerId}/games/"})
	public List<GameDTO> getGamesByPlayer(@PathVariable Integer playerId) {
		return gameService.getGamesByPlayerId(playerId);
	}
	
	// players -----
	@RequestMapping(method=RequestMethod.GET,value={"/players/"})
	public List<PlayerDTO> findAll(){
		return playerService.findAllDTO();
	}	
	
//	@RequestMapping(method=RequestMethod.GET,value={"/players/ranking"})
//	public List<PlayerDTO> rankAverageSuccess(){
//		return playerService.rankAverageSuccess();
//	}	
	
//	@RequestMapping(method=RequestMethod.GET,value={"/players/ranking/"})
//	public List<PlayerDTO> findAllViewPlayers(){
//		return viewPlayerRepo.calculateSuccessRate();
//	}	
}
