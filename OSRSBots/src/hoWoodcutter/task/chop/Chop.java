package hoWoodcutter.task.chop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Chop extends Node {

	public Chop(AbstractScript script) {
		super(script);
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
		GameObject tree = script.getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals(Settings.location.getTree().getTreeName())
				&& gameObject.hasAction("Chop down"));

		if(tree.interact("Chop down")) {
			int countLog = script.getInventory().count(Settings.location.getTree().getLogName());
			AbstractScript.sleepUntil(() -> script.getInventory().count(Settings.location.getTree().getLogName()) > countLog, 
					Calculations.random(11000, 13000));
		}
	}

}
