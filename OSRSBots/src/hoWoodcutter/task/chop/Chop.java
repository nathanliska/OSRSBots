package hoWoodcutter.task.chop;

import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Chop extends Node {

	public Chop(AbstractScript _script) {
		super(_script);
	}

	@Override
	public String status() {
		return "Chopping down trees.";
	}
	
	@Override
	public boolean validate() {
		return (!script.getInventory().isFull() && Settings.location.getTreeArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		// TODO CHOP DOWN TREES IN TREE AREA.
		
	}

}
