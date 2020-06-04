package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<PlayerDTO> findAll(){
		List<PlayerDTO> dtos = 
				playerRepo.findAll()
				.stream()
				.map(player -> convertToDTO(player))
				.collect(Collectors.toList());
		dtos
		.stream()
		.forEach(dto -> dto.setSuccessrate(gameRepo.calculateSuccessRate(dto.getId())));
		
		return dtos;
	}
	
	public List<PlayerDTO> findAllDTO(){
		return playerRepo.findAllDTO();
	}
	
	
	public PlayerDTO save(PlayerDTO dto) {
		return convertToDTO( playerRepo.save( convertToPlayer( dto ) ) );
	}
	
	
	public PlayerDTO convertToDTO(Player player) {
		PlayerDTO dto = new PlayerDTO();
		dto.setId(player.getId());
		dto.setName(player.getName());
		dto.setRegistrationDateTime(player.getRegistrationDateTime());
		//dto.setSuccessrate(player.getSuccessrate());
		return dto;
	}
	
	public Player convertToPlayer(PlayerDTO dto) {
		Player player;
		if(dto.isAnonymous()) {
			player = new AnonymousPlayer();
		}else {
			player = new KnownPlayer(dto.getName());
		}
		if(dto.getId()!=null) {
			player.setId(dto.getId());
		}
		// registrationDateTime will care for itself automatically
		return player;
	}
}
