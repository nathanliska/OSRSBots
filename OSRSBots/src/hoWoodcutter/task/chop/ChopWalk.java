package hoWoodcutter.task.chop;

import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class ChopWalk extends Node {

	public ChopWalk(AbstractScript _script) {
		super(_script);
	}

	@Override
	public String status() {
		return "Walking to trees.";
	}
	
	@Override
	public boolean validate() {
		return (!script.getInventory().isFull() && Settings.location.getTreeArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		// TODO WALK TO TREE AREA AND CUT SPECIFIED TREE.
		
	}

}
