package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private Trees tree;
	private TreeAreas treeArea;
	private BankAreas bankArea;
	
	public Locations(String _tree, String _treeArea, String _bankArea) {
		this.tree = parseToTree(_tree);
		this.treeArea = parseToTreeArea(_treeArea);
		this.bankArea = parseToBankArea(_bankArea);
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
	
	public Trees parseToTree(String _tree) {
		for(int i = 0; i < Trees.values().length; i++) {
			if(Trees.values()[i].getTreeName().equals(_tree)) {
				return Trees.values()[i];
			}
		}
		return null;
	}
	
	public TreeAreas parseToTreeArea(String _treeArea) {
		for(int i = 0; i < TreeAreas.values().length; i++) {
			if(TreeAreas.values()[i].getStringArea().equals(_treeArea)) {
				return TreeAreas.values()[i];
			}
		}
		return null;
	}
	
	public BankAreas parseToBankArea(String _bankArea) {
		for(int i = 0; i < BankAreas.values().length; i++) {
			if(BankAreas.values()[i].getStringArea().equals(_bankArea)) {
				return BankAreas.values()[i];
			}
		}
		return null;
	}
}