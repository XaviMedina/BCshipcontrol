package xavi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xavi.model.Faction;
import xavi.model.Player;
import xavi.model.Ship;
import xavi.model.ShipType;
import xavi.service.PlayerService;
import xavi.service.ShipService;

@RequestMapping("/operations")
@Controller
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ShipService shipService;
	
	@RequestMapping(value="/test",produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Player TestJSON(){
		Player player = new Player();
		player.setId(1l);
		player.setName("Test");
		player.setGuild("Test");
		player.setFaction(Faction.GREEN);
		return player;
	}
	
	@RequestMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Player> listPlayers(){
		return playerService.findAll();
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public String updatePlayer(@RequestParam("id")long id, @RequestParam("name")String name, @RequestParam("guild") String guild,
			@RequestParam("faction") String faction){
		Player player = playerService.findById(id);
		if (player == null){
			return "fail";
		}
		else{
			player.setName(name);
			player.setGuild(guild);
			player.setFaction(Faction.valueOf(faction));
			playerService.update(player);
			return "success";
		}
	}
	
	@RequestMapping(value="/addPlayer")
	@ResponseBody
	public String addPlayer(@RequestParam("name")String name, @RequestParam("guild") String guild,
			@RequestParam("faction") String faction, @RequestParam("ship_name") String ship_name,
			@RequestParam("ship_type") String ship_type){
		String response = "";
		Player player = playerService.findByName(name);
		if(player == null){
			player = new Player();
			player.setName(name);
			player.setGuild(guild);
			player.setFaction(Faction.valueOf(faction));
			player = playerService.create(player);
			Ship ship = new Ship();
			ship.setName(ship_name);
			ship.setType(ShipType.valueOf(ship_type));
			ship.setPlayer(player);
			shipService.create(ship);
			response =  "success_1";
		}
		else{
			boolean shipAlreadyAdded = false;
			if(player.getShips() != null && player.getShips().size() > 0){
				for(Ship ship: player.getShips()){
					if(ship.getName().equals(ship_name) && ship.getType().equals(ShipType.valueOf(ship_type))){
						shipAlreadyAdded = true;
					}
				}
				if(shipAlreadyAdded){
					response = "success_2";
				}
			}
			if(player.getShips() == null || shipAlreadyAdded == false){
				Ship ship = new Ship();
				ship.setName(ship_name);
				ship.setType(ShipType.valueOf(ship_type));
				ship.setPlayer(player);
				shipService.create(ship);
				response = "success_3";
			}
		}
		return response;
		
	}
	
	@RequestMapping("/addShip")
	@ResponseBody
	public String addShip(@RequestParam("player_name") String player_name,@RequestParam("ship_name") String shipName,
			@RequestParam("ship_type") String shipType){
		Player player = playerService.findByName(player_name);
		if(player == null){
			return "fail";
		}
		Ship ship = new Ship();
		ship.setName(shipName);
		ship.setPlayer(player);
		ship.setType(ShipType.valueOf(shipType));
		ship = shipService.create(ship);
		return "success";
		
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public List<Player> searchPlayers(@RequestParam("name")String pattern){
		return playerService.findAllByName(pattern);
	}
}
