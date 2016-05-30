package hoWoodcutter.core;

public class Settings {

	//Creates instantiation of Locations
	private Locations location;
	private boolean worldHopIfAttacked;
	private boolean worldHopIfNoTrees;
	private boolean powerChop;
	
	public Settings() {
		location = null;
		worldHopIfAttacked = false;
		worldHopIfNoTrees = false;
		powerChop = false;
	}
	
	public void setLocations(Locations location) {
		this.location = location;
	}
	
	public void setWorldHopIfAttacked(boolean hop) {
		this.worldHopIfAttacked = hop;
	}
	
	public void setWorldHopIfNoTrees(boolean hop) {
		this.worldHopIfNoTrees = hop;
	}
	
	public void setPowerChop(boolean chop) {
		this.powerChop = chop;
	}
	
	public Locations getLocations() {
		return location;
	}
	
	public boolean getWorldHopIfAttacked() {
		return worldHopIfAttacked;
	}
	
	public boolean getWorldHopIfNoTrees() {
		return worldHopIfNoTrees;
	}
	
	public boolean getPowerChop() {
		return powerChop;
	}
}