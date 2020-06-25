package com.example.demo.controllers;

import java.util.List;

import com.example.demo.dtos.ProjectionPlaya;
import com.example.demo.tools.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.GameDTO;
import com.example.demo.dtos.PlayerDTO;
import com.example.demo.services.GameService;
import com.example.demo.services.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	@Autowired
	GameService gameService;
	
	//////////////
	
	@RequestMapping(method=RequestMethod.POST,value={"/players/{playerId}/games"})
	public GameDTO play(@PathVariable Integer playerId) {
		return gameService.play(playerId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value={"/players/{playerId}/games"})
	public ResponseEntity<String> delete(@PathVariable Integer playerId) {
		gameService.delete(playerId);
		return new ResponseEntity<String>("Deleted all Games of the Player with the id: " + playerId, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/players/{playerId}/games"})
	public List<GameDTO> getGamesByPlayer(@PathVariable Integer playerId) {
		return gameService.getGamesByPlayerId(playerId);
	}
	
	// players ///////
	@JsonView(View.Short.class)
	@RequestMapping(method=RequestMethod.GET,value={"/players"})
	public List<PlayerDTO> findAll(){
		return playerService.findAll();
	}

	@JsonView(View.All.class)
	@RequestMapping(method=RequestMethod.POST,value={"/players"})
	public PlayerDTO createPlayer(@RequestBody PlayerDTO dto){
		return playerService.save(dto);
	}

	@JsonView(View.All.class)
	@RequestMapping(method=RequestMethod.PUT,value={"/players"})
	public PlayerDTO modifiy(@RequestBody PlayerDTO dto){
		return playerService.modifiy( dto );
	}

	@RequestMapping(method=RequestMethod.DELETE,value={"/players/{playerId}"})
	public Integer deletePlayer(@PathVariable Integer playerId) {
		playerService.deletePlayer(playerId);
		return playerId;
	}

	// ranking ///////
	@RequestMapping(method=RequestMethod.GET,value={"/players/ranking"})
	public Double ranking(){
		return playerService.ranking();
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/players/ranking/loser"})
	public List<ProjectionPlaya> findLoser(@RequestParam(required = false, defaultValue = "1") int limit){
		return playerService.findLoser( limit );
	}

	@RequestMapping(method=RequestMethod.GET,value={"/players/ranking/winner"})
	public List<ProjectionPlaya> findWinner(@RequestParam(required = false, defaultValue = "1") int limit){
		return playerService.findWinner( limit );
	}
}
