package xavi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import xavi.model.Player;

public interface PlayerRepository extends CrudRepository<Player,Long>{

	@Query("SELECT p FROM Player p WHERE LOWER(p.name) LIKE %LOWER(:name)%")
	public List<Player> findByName(@Param("name") String name);
	
	@Query("SELECT p FROM Player p WHERE p.name == :name")
	public Player findUniquePlayerByName(@Param("name") String name);
}
