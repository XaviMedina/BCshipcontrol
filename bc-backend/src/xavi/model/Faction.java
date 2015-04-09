package xavi.model;

public enum Faction {

	RED("red"),
	GREEN("green");
	
	private String faction;
	
	private Faction(String faction){
		this.setFaction(faction);
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}
}
