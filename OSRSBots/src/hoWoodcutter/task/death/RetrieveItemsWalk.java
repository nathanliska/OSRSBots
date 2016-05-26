package hoWoodcutter.task.death;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class RetrieveItemsWalk extends Node {

	public RetrieveItemsWalk(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Running away!";
	}

	@Override
	public boolean validate() {
		return (script.getLocalPlayer().isInCombat() && script.getLocalPlayer().getHealth() == 0);
	}

	@Override
	public void execute() {
		script.setDeathSpot(script.getLocalPlayer().getTile());
		while (!script.getLocalPlayer().getTile().equals(script.getDeathSpot())) {
			if (script.getWalking().walk(script.getDeathSpot())) {
				AbstractScript.sleep(Calculations.random(650, 850)); // buffer
																		// so it
																		// doesn't
																		// double
																		// click
																		// before
																		// movement
																		// starts
				AbstractScript.sleepWhile(new Condition() {

					@Override
					public boolean verify() {
						return script.getClient().getLocalPlayer().isMoving();
					}
				}, Calculations.random(9000, 11000));
			}
		}
	}
}
