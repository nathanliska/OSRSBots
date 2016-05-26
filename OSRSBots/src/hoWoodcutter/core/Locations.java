package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private Trees tree;
	private BankAreas bankArea;
	private TreeAreas treeArea;
	
	Locations(Trees _tree, BankAreas _bankArea, TreeAreas _treeArea) {
		this.tree = _tree;
		this.bankArea = _bankArea;
		this.treeArea = _treeArea;
	}
	
	public Trees getTree() {
		return tree;
	}
	
	public BankAreas getBankArea() {
		return bankArea;
	}
	
	public TreeAreas getTreeArea() {
		return treeArea;
	}
	
}