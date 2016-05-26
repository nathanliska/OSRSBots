package hoWoodcutter.core;

public class Settings {

	//Creates instantiation of Locations
	private Locations location;
	
	public Settings() {
		location = null;
	}
	
	public void setLocations(Locations location) {
		this.location = location;
	}
	
	public Locations getLocations() {
		return location;
	}
}