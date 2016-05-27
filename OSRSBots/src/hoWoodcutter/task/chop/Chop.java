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
		return (!script.getInventory().isFull()
				&& script.getSettings().getLocations().getTreeArea().getArea().contains(script.getLocalPlayer()));
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
								&& script.getMap().canReach(gameObject)
								&& gameObject.hasAction("Chop down"));

		// should probably make sure you don't cut down trees that are visible
		// but out of the bounding box, keeps happening to me

		if (tree.interact("Chop down")) {
			AbstractScript.sleepUntil(
					() -> !tree.exists() || script.getInventory().isFull() || script.getLocalPlayer().isInCombat(), // need
																													// to
																													// find
																													// a
																													// way
																													// to
																													// add
																													// onlevelup
																													// and
																													// onrandom
					Calculations.random(30000, 45000));
		} else {
			script.getWalking().walk(script.getSettings().getLocations().getTreeArea().getArea().getCenter());
		}
	}

}
