package hoWoodcutter.task.chop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Chop extends Node {

	public Chop(AbstractScript script, Settings settings) {
		super(script, settings);
	}

	@Override
	public String status() {
		return "Chopping down trees.";
	}
	
	@Override
	public boolean validate() {
		return (!script.getInventory().isFull() && settings.getLocation().getTreeArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		GameObject tree = script.getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals(settings.getLocation().getTree().getTreeName())
				&& gameObject.hasAction("Chop down"));

		//should probably make sure you don't cut down trees that are visible but out of the bounding box, keeps happening to me
		
		if(tree.interact("Chop down")) {
			int countLog = script.getInventory().count(settings.getLocation().getTree().getLogName());
			AbstractScript.sleepUntil(() -> script.getInventory().count(settings.getLocation().getTree().getLogName()) > countLog, 
					Calculations.random(11000, 13000));
		}
	}

}
