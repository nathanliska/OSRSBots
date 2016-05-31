package hoWoodcutter.task.chop;

import java.util.ArrayList;
import java.util.List;

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
		GameObject tree = script.getGameObjects()
				.closest(
						gameObject -> gameObject != null
								&& gameObject.getName()
										.equals(script.getSettings().getLocations().getTree().getTreeName())
								&& script.getSettings().getLocations().getTreeArea().getArea().contains(gameObject)
								&& script.getMap().canReach(gameObject) && gameObject.hasAction("Chop down"));

		if (tree != null) {
			try {
				if (tree.interact("Chop down")) {
					AbstractScript.sleep(Calculations.random(1750, 2000));
					while (script.getLocalPlayer().isMoving()) {
						AbstractScript.sleep(Calculations.random(1750, 2000));
					}
					AbstractScript.sleepUntil(
							() -> !tree.exists() || script.getInventory().isFull()
									|| script.getLocalPlayer().isInCombat() || !script.getLocalPlayer().isAnimating(),
							// need to find a way to add on random
							Calculations.random(100000, 150000));
					AbstractScript.sleep(Calculations.random(250, 750));
				} else {
					script.getWalking().walk(script.getSettings().getLocations().getTreeArea().getArea().getCenter());
				}
			} catch (NullPointerException e) {
				// tree disappeared before I got there!
			}
		} else {
			if (script.getSettings().getWorldHopIfNoTrees()) {
				script.setWorldHop(true);
			}
		}
	}
}
