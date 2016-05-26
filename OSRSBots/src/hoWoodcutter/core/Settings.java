package hoWoodcutter.core;

public class Settings {

	//Creates references to Locations
	public static Locations location;
	
	public Settings() {
		//nothing
	}
	
	public Locations getLocation() {
		return location;
	}
	
	public void SetLocations(Locations location) {
		Settings.location = location;
	}
	
}