package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Settings {

	//Creates references to Locations
	private Locations locations;
	
	private String[] treeNames = new String[Trees.values().length];
	
	private String[] bankNames = new String[BankAreas.values().length];
	
	private String[] treeAreaNames = new String[TreeAreas.values().length];
	
	public Settings() {
		//nothing
	}
	
	public Locations getLocations() {
		return locations;
	}
	
	public String[] getAllTreeNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			treeNames[i] = Trees.values()[i].getTreeName();
		}
		return treeNames;
	}
	
	public String[] getAllBankNames() {
		for (int i = 0; i < BankAreas.values().length; i++) {
			bankNames[i] = BankAreas.values()[i].getStringArea();
		}
		return bankNames;
	}
	
	public String[] getAllTreeAreaNames() {
		for (int i = 0; i < TreeAreas.values().length; i++) {
			treeAreaNames[i] = TreeAreas.values()[i].getStringArea();
		}
		return treeAreaNames;
	}
}
