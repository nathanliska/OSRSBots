package hoWoodcutter.core;

import java.util.LinkedList;
import java.util.Queue;

import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

public class Locations {

	private final Trees tree;
	private final TreeAreas treeArea;
	private final BankAreas bankArea;
	
	public Locations(Trees tree, TreeAreas treeArea, BankAreas bankArea) {
		this.tree = tree;
		this.treeArea = treeArea;
		this.bankArea = bankArea;
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