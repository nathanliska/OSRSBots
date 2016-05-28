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
	public String status() {
		return "Chopping down trees.";
	}

	@Override
	public boolean validate() {
		return (!script.getInventory().isFull()
				&& script.getSettings().getLocations().getTreeArea().getArea().contains(script.getLocalPlayer())
				&& !script.getLevelUp());
	}

	@Override
	public void execute() {
		// if(script.getSettings().getLocations().getTreeArea().getArea().contains(*Tree
		// That Exists*)) //trying to check if the area has a tree in it that
		// isn't cut down
		GameObject tree = script.getGameObjects()
				.closest(
						gameObject -> gameObject != null
								&& gameObject.getName()
										.equals(script.getSettings().getLocations().getTree().getTreeName())
								&& script.getSettings().getLocations().getTreeArea().getArea().contains(gameObject)
								&& script.getMap().canReach(gameObject) && gameObject.hasAction("Chop down"));

		// should probably make sure you don't cut down trees that are visible
		// but out of the bounding box, keeps happening to me

		if (tree.interact("Chop down")) {
			AbstractScript.sleepUntil(
					() -> !tree.exists() || script.getInventory().isFull() || script.getLocalPlayer().isInCombat()
							|| !script.getLocalPlayer().isAnimating(),
					// need to find a way to add on random
					Calculations.random(100000, 150000));
			AbstractScript.sleep(Calculations.random(250, 750));
		} else {
			script.getWalking().walk(script.getSettings().getLocations().getTreeArea().getArea().getCenter());
		}
	}

}
