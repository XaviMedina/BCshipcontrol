package xavi.service;

import java.util.List;

import xavi.model.Ship;


public interface ShipService {
	public Ship create(Ship ship);
	public Ship delete(Ship ship);
	public List<Ship> findAll();
	public Ship update(Ship ship);
	public Ship findById(long id);
}
