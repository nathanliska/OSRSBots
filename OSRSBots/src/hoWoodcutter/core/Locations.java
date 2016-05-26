package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private Trees tree;
	private TreeAreas treeArea;
	private BankAreas bankArea;
	
	Locations(Trees _tree, TreeAreas _treeArea, BankAreas _bankArea) {
		this.tree = _tree;
		this.bankArea = _bankArea;
		this.treeArea = _treeArea;
	}
	
	public Trees getTree() {
		return tree;
	}
	
	public TreeAreas getTreeArea() {
		return treeArea;
	}
	
	public BankAreas getBankArea() {
		return bankArea;
	}
}