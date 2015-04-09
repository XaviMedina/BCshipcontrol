package xavi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xavi.model.Ship;
import xavi.repository.ShipRepository;
import xavi.service.ShipService;

@Service("shipService")
public class ShipServiceImpl implements ShipService {

	@Resource
	private ShipRepository shipRepository;
	
	public Ship create(Ship ship) {
		Ship createdShip = ship;
		return shipRepository.save(createdShip);
	}

	public Ship delete(Ship ship) {
		Ship deletedShip = shipRepository.findOne(ship.getId());
		
		if(deletedShip == null){
			try {
				throw new SQLException("Ship does not exist.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		shipRepository.delete(ship);
		
		return deletedShip;
	}

	public List<Ship> findAll() {
		return (List<Ship>) shipRepository.findAll();
	}

	public Ship update(Ship ship) {
		Ship updatedShip = shipRepository.findOne(ship.getId());
		
		if(updatedShip == null){
			try {
				throw new SQLException("Ship does not exist.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		updatedShip.setName(ship.getName());
		updatedShip.setPlayer(ship.getPlayer());
		updatedShip.setType(ship.getType());
		updatedShip = shipRepository.save(updatedShip);
		return updatedShip;
	}

	public Ship findById(long id) {
		return shipRepository.findOne(id);
	}
	

}
