package hoWoodcutter.core;

public class Settings {

	//Creates instantiation of Locations
	private Locations location;
	private boolean worldHop;
	
	public Settings() {
		location = null;
		worldHop = false;
	}
	
	public void setLocations(Locations location) {
		this.location = location;
	}
	
	public Locations getLocations() {
		return location;
	}
	
	public void setWorldHop(boolean hop) {
		this.worldHop = hop;
	}
	
	public boolean getWorldHop() {
		return worldHop;
	}
}