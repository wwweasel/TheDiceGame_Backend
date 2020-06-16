package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dtos.ProjectionPlaya;
import com.example.demo.dtos.ProjectionPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PlayerDTO;
import com.example.demo.entities.AnonymousPlayer;
import com.example.demo.entities.KnownPlayer;
import com.example.demo.entities.Player;
import com.example.demo.repos.GameRepository;
import com.example.demo.repos.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	// Return Player and its successrate in a PlayerDTO
	public List<PlayerDTO> findAll(){
		List<PlayerDTO> dtos = 
				playerRepo.findAll()
				.stream()
				.map(player -> convertToDTO(player))
				.collect(Collectors.toList());

		dtos
		.stream()
		.forEach(dto -> {
			if(gameRepo.calculateSuccessRate(dto.getId()).isPresent()){
				dto.setSuccessrate(gameRepo.calculateSuccessRate(dto.getId()).get());
			}
		});

		return dtos;
	}
	
	public PlayerDTO save(PlayerDTO dto) {
		return convertToDTO( playerRepo.save( convertToPlayer( dto ) ) );
	}

	// So far only modifies the name of the Player
	public PlayerDTO modifiy(PlayerDTO dto){
		Optional<? extends Player> playerO = playerRepo.findById(dto.getId());

		playerO.ifPresentOrElse( player -> {
			player.setName(dto.getName());
		}, () -> {
			throw new RuntimeException("Shoot!");
		});
		return convertToDTO( playerRepo.save(playerO.get()) );
	}

	// delete a Player
	public void deletePlayer(Integer playerId){
		playerRepo.deleteById(playerId);
	}

	// ranking
	public Double ranking(){
		Optional<Double> successrateAllO = gameRepo.calculateSuccessRateAll();
		if(successrateAllO.isPresent()){
			return successrateAllO.get();
		}else {
			throw new RuntimeException("No Games, no ranking.");
		}
	}

//	public List<PlayerDTO> findLoser(){
//		return playerRepo.findLoser()
//				.stream()
//				.map( player -> convertToDTO(player))
//				.collect(Collectors.toList());
//	}
	public List<ProjectionPlaya> findLoser(){
		return playerRepo.findLoser();
	}

	public List<ProjectionPlayer> findTestLoser(){
		return playerRepo.findTestLoser();
	}

	
	public PlayerDTO convertToDTO(Player player) {
		PlayerDTO dto = new PlayerDTO();
		dto.setId(player.getId());
		dto.setName(player.getName());
		dto.setRegistrationDateTime(player.getRegistrationDateTime());
		if(dto.getName() != "Anonymous")
			dto.setAnonymous(false);
		return dto;
	}
	
	public Player convertToPlayer(PlayerDTO dto) {
		Player player;
		if(dto.isAnonymous()) {
			player = new AnonymousPlayer();
		}else {
			player = new KnownPlayer(dto.getName());
		}
		// registrationDateTime will care for itself automatically
		return player;
	}
}
