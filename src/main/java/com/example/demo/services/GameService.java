package com.example.demo.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.GameDTO;
import com.example.demo.entities.Game;
import com.example.demo.repos.GameRepository;
import com.example.demo.repos.PlayerRepository;


@Service
public class GameService {
	@Autowired
	GameRepository gameRepo;
	@Autowired
	PlayerRepository playerRepo;
	
	public List<GameDTO> findAll(){
		return gameRepo.findAll()
				.stream()
				.map(game -> convertToDTO(game))
				.collect(Collectors.toList());
	}
	
	public GameDTO save(GameDTO dto) {
		return convertToDTO( gameRepo.save( convertToGame( dto ) ) );
	}
	
	// ------
	public GameDTO play(Integer playerId) {
		GameDTO dto = new GameDTO();
		dto.setPlayerId(playerId);
		dto.setResult(getRandomNumber(1,6)+getRandomNumber(1,6));
		dto.setSuccess( dto.getResult() == 7 );
		return save(dto);
	}
	
	public int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	
	//-------
	public void delete(Integer playerId) {
		List<Game> games = gameRepo.getGamesByPlayerId(playerId);
		for (Game game : games) {
			gameRepo.delete(game);
		}
	}
	
	//------
	
	public List<GameDTO> getGamesByPlayerId(Integer playerId){
		
		return gameRepo.getGamesByPlayerId(playerId)
				.stream()
				.map(game -> convertToDTO(game))
				.collect(Collectors.toList());
	}
	
	//-----
	
//	public List<GameDTO> getGamesAndSuccessRate() {
//		List<GameDTO> 
//	}
	
	//----
	
	public GameDTO convertToDTO(Game game) {
		GameDTO dto = new GameDTO();
		dto.setId(game.getId());
		dto.setPlayerId(game.getPlayer().getId());
		dto.setResult(game.getResult());
		dto.setSuccess(game.isSuccess());
		return dto;
	}
	
	public Game convertToGame(GameDTO dto) {
		Game game = new Game();
		if(dto.getId()!=null) {
			game.setId(dto.getId());
		}
		game.setPlayer(playerRepo.findById( dto.getPlayerId() ).get() );
		game.setResult(dto.getResult());
		game.setSuccess(dto.isSuccess());
		return game;
	}
}
