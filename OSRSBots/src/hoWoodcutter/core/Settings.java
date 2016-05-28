package hoWoodcutter.core;

public class Settings {

	//Creates instantiation of Locations
	private Locations location;
	private boolean worldHop;
	private boolean powerChop;
	
	public Settings() {
		location = null;
		worldHop = false;
		powerChop = false;
	}
	
	public void setLocations(Locations location) {
		this.location = location;
	}
	
	public void setWorldHop(boolean hop) {
		this.worldHop = hop;
	}
	
	public void setPowerChop(boolean chop) {
		this.powerChop = chop;
	}
	
	public Locations getLocations() {
		return location;
	}
	
	public boolean getWorldHop() {
		return worldHop;
	}
	
	public boolean getPowerChop() {
		return powerChop;
	}
}