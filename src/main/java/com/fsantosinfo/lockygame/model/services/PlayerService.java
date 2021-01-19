package com.fsantosinfo.lockygame.model.services;

import java.util.List;
import java.util.Optional;

import com.fsantosinfo.lockygame.model.entities.LuckyGame;
import com.fsantosinfo.lockygame.model.entities.Player;
import com.fsantosinfo.lockygame.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This class will help with some the business logic

@Service
public class PlayerService {

    @Autowired
	private PlayerRepository repository;
	
	@Autowired
	private LuckyGameService gameService;

	public List<Player> findAll() {
		return repository.findAll();
	}

	public void save(Player player) {

        repository.save(player);
	}

	public Player findById(Long id) {        
        Optional<Player> optionalPlayer = repository.findById(id);
        return optionalPlayer.get();
	}

	public LuckyGame gettingTheGame(Long id) {
		return gameService.findById(id);
	}

	public void insertPlayerAndGame(LuckyGame lucky, Long idPlayer) {
		repository.insertPlayerAndGame(idPlayer, lucky.getId());
}

	public boolean alreadyPlayer(Long gameId, Long playerId) {
		
		Boolean logicTest = false;
		List<Player> players = gameService.findById(gameId).getPlayers();		
		
		
		for (Player player : players){
			if (player.getId().equals(playerId)){
			logicTest = true;
			}
		}
		
		return logicTest;
	}
}
