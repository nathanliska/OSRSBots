package hoWoodcutter.core;

import hoWoodcutter.util.Trees;

public class Settings {

	//Creates references to Locations
	private Locations locations;
	
	private String[] names = new String[Trees.values().length];
	
	public Locations getLocations() {
		return locations;
	}
	
	public String[] getAllTreeNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			names[i] = Trees.values()[i].getTreeName();
		}
		return names;
	}
	
}
