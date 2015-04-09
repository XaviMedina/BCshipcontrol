package xavi.model;

public enum ShipType {

	GALLEON("galleon"),
	TRADE_SHIP("tradeship"),
	FISHING_BOAT("fishing boat"),
	ROWBOAT("rowboat"),
	BLACKPEARL("blackpearl");
	
	private String type;
	
	private ShipType(String type){
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
