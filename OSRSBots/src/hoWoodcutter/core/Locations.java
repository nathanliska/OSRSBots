package hoWoodcutter.core;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private Trees tree;
	private TreeAreas treeArea;
	private BankAreas bankArea;
	
	public Locations(String tree, String treeArea, String bankArea) {
		this.tree = parseToTree(tree);
		this.treeArea = parseToTreeArea(treeArea);
		this.bankArea = parseToBankArea(bankArea);
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
	
	public Trees parseToTree(String tree) {
		for(int i = 0; i < Trees.values().length; i++) {
			if(Trees.values()[i].getTreeName().equals(tree)) {
				return Trees.values()[i];
			}
		}
		return null;
	}
	
	public TreeAreas parseToTreeArea(String treeArea) {
		for(int i = 0; i < TreeAreas.values().length; i++) {
			if(TreeAreas.values()[i].getStringArea().equals(treeArea)) {
				return TreeAreas.values()[i];
			}
		}
		return null;
	}
	
	public BankAreas parseToBankArea(String bankArea) {
		for(int i = 0; i < BankAreas.values().length; i++) {
			if(BankAreas.values()[i].getStringArea().equals(bankArea)) {
				return BankAreas.values()[i];
			}
		}
		return null;
	}
}