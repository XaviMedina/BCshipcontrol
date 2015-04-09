package xavi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="ships")
public class Ship {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name="player_id")
	@JsonBackReference
	private Player player;
	
	@Column(name="ship_name")
	private String name;
	@Column(name="ship_type")
	private ShipType type;
	
	public Ship(){
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ShipType getType() {
		return type;
	}
	public void setType(ShipType type) {
		this.type = type;
	}
	
	
	
}
