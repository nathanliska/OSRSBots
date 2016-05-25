package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;

public class Locations {

	private BankAreas bankArea;
	private TreeAreas treeArea;
	
	Locations(BankAreas _bankArea, TreeAreas _treeArea) {
		this.bankArea = _bankArea;
		this.treeArea = _treeArea;
	}
	
	public BankAreas getBankArea() {
		return bankArea;
	}
	
	public TreeAreas getTreeArea() {
		return treeArea;
	}
	
}
