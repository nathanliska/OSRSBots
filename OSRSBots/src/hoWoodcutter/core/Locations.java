package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private String tree;
	private String treeArea;
	private String bankArea;
	
	public Locations(String tree, String _treeArea, String _bankArea) {
		this.tree = tree;
		this.bankArea = bankArea;
		this.treeArea = treeArea;
	}
	
	public String getTree() {
		return tree;
	}
	
	public String getTreeArea() {
		return treeArea;
	}
	
	public String getBankArea() {
		return bankArea;
	}
}