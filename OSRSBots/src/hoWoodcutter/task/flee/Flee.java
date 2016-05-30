package hoWoodcutter.task.flee;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import hoWoodcutter.task.Node;
import hoWoodcutter.hoWoodcutter;

public class Flee extends Node {

	public Flee(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Running away!";
	}

	@Override
	public boolean validate() {
		return (script.getLocalPlayer().isInCombat());
	}

	@Override
	public void execute() {
		if (!script.getSettings().getLocations().getBankArea().getArea().contains(script.getLocalPlayer())) {
			// runs away if in bank
			while (script.getLocalPlayer().isInCombat()) {
				if (script.getWalking()
						.walk(script.getSettings().getLocations().getBankArea().getArea().getRandomTile())) {
					AbstractScript.sleep(Calculations.random(650, 850));
					// buffer so it doesn't double click before movement starts
					AbstractScript.sleepUntil(() -> !script.getLocalPlayer().isMoving(),
							Calculations.random(3000, 5000));
				}
			}
			AbstractScript.sleep(Calculations.random(6000, 7000));
		} else if (!script.getSettings().getLocations().getTreeArea().getArea().contains(script.getLocalPlayer())) {
			// runs away if in tree area
			while (script.getLocalPlayer().isInCombat()) {
				if (script.getWalking()
						.walk(script.getSettings().getLocations().getTreeArea().getArea().getRandomTile())) {
					AbstractScript.sleep(Calculations.random(650, 850));
					// buffer so it doesn't double click before movement starts
					AbstractScript.sleepUntil(() -> !script.getLocalPlayer().isMoving(),
							Calculations.random(3000, 5000));
					// temp world hop until we make a node for it
				}
			}
			AbstractScript.sleep(Calculations.random(6000, 7000));
		}
		if (script.getSettings().getWorldHopIfAttacked()) {
			script.setWorldHop(true);
		}
	}
}
