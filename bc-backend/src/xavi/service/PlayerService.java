package xavi.service;

import java.util.List;

import xavi.model.Player;


public interface PlayerService {
	public Player create(Player player);
	public Player delete(Player player);
	public List<Player> findAll();
	public Player update(Player player);
	public Player findById(long id);
	public Player findByName(String name);
	public List<Player> findAllByName(String name);
}
