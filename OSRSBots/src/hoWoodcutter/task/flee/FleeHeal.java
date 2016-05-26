package hoWoodcutter.task.flee;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class FleeHeal extends Node {
	
	public FleeHeal(AbstractScript script) {
		super(script);
	}

	@Override
	public String status() {
		return "Waiting to heal.";
	}

	@Override
	public boolean validate() {
		return (script.getLocalPlayer().getHealthPercent() < 90
				&& Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()) && !script.getInventory().isFull());
	}

	@Override
	public void execute() {
		AbstractScript.log("Waiting to heal. health at " + script.getLocalPlayer().getHealth() + "  / "
				+ script.getLocalPlayer().getMaxHealth());
		AbstractScript.sleepUntil(new Condition() {

			@Override
			public boolean verify() {
				return script.getLocalPlayer().getHealthPercent() > 90;
			}
		}, Calculations.random(250000, 280000));
		script.getCamera().rotateToTile(Settings.location.getBankArea().getArea().getRandomTile());
	}
}
