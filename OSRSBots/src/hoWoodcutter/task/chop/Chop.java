package hoWoodcutter.task.chop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class Chop extends Node {

	public Chop(hoWoodcutter script) {
		super(script);
	}

	@Override
	public int delay() {
		// TODO: CHANGE DELAYS DEPENDING ON TYPE OF TREE.
		
		return 0;
	}
	
	@Override
	public String status() {
		return "Chopping down trees.";
	}
	
	@Override
	public boolean validate() {
		return (!script.getInventory().isFull() && script.getSettings().getLocations().getTreeArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		GameObject tree = script.getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals(script.getSettings().getLocations().getTree().getTreeName())
				&& gameObject.hasAction("Chop down"));

		//should probably make sure you don't cut down trees that are visible but out of the bounding box, keeps happening to me
		
		if(tree.interact("Chop down") && script.getSettings().getLocations().getTreeArea().getArea().contains(tree.getTile())) {
			AbstractScript.sleepUntil(() -> !tree.exists() || script.getInventory().isFull(), 
					Calculations.random(30000, 45000));
		} else {
			script.getWalking().walk(script.getSettings().getLocations().getTreeArea().getArea().getCenter());
		}
	}

}
