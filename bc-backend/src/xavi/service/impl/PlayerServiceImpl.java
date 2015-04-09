package xavi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xavi.model.Player;
import xavi.repository.PlayerRepository;
import xavi.repository.ShipRepository;
import xavi.service.PlayerService;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

	@Resource
	private PlayerRepository playerRepository;
	
	@Resource
	private ShipRepository shipsRepository;
	
	public Player create(Player player) {
		Player createdPlayer = player;
		return playerRepository.save(createdPlayer);
	}

	public Player delete(Player player) {
		Player deletedPlayer = playerRepository.findOne(player.getId());
		
		if(deletedPlayer == null){
			try {
				throw new SQLException("Player does not exist.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		playerRepository.delete(player);
		
		return deletedPlayer;
	}

	public List<Player> findAll() {
		return (List<Player>) playerRepository.findAll();
	}

	public Player update(Player player) {
		Player updatedPlayer = playerRepository.findOne(player.getId());
		
		if(updatedPlayer == null){
			try {
				throw new SQLException("Player does not exist.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		updatedPlayer.setName(player.getName());
		updatedPlayer.setGuild(player.getGuild());
		updatedPlayer.setFaction(player.getFaction());
		updatedPlayer = playerRepository.save(updatedPlayer);
		return updatedPlayer;
	}

	public Player findById(long id) {
		return playerRepository.findOne(id);
	}

	public Player findByName(String name) {
		Iterable<Player> players = playerRepository.findAll();
		for(Player player : players){
			if(player.getName().equals(name)){
				return player;
			}
		}
		return null;
	}
	
	public List<Player> findAllByName(String name){
		return playerRepository.findByName(name);
	}

}
