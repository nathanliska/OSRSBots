package hoWoodcutter.task.flee;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Flee extends Node {
	
	public Flee(AbstractScript script) {
		super(script);
	}

	@Override
	public String status() {
		return "Running away!";
	}

	@Override
	public boolean validate() {
		return (script.getLocalPlayer().isInCombat()
				&& Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		if(script.getWalking().walk(Settings.location.getBankArea().getArea().getRandomTile())) {
			AbstractScript.sleep(Calculations.random(650, 850)); //buffer so it doesn't double click before movement starts
			AbstractScript.sleepWhile(new Condition() {
				
				@Override
				public boolean verify() {
					return script.getClient().getLocalPlayer().isMoving();
				}
			}, Calculations.random(9000, 11000));
		}
	}
}
