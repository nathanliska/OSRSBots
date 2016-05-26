package hoWoodcutter.core;

public class Settings {

	//Creates references to Locations
	private final Locations location;
	
	public Settings(Locations location) {
		this.location = location;
	}
	
	public Locations getLocation() {
		return location;
	}
	
}