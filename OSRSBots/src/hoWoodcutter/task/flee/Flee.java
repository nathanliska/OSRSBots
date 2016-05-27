package hoWoodcutter.task.flee;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

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
		return (script.getLocalPlayer().isInCombat()
				&& !script.getSettings().getLocations().getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		if(script.getWalking().walk(script.getSettings().getLocations().getBankArea().getArea().getRandomTile())) {
			AbstractScript.sleep(Calculations.random(650, 850)); //buffer so it doesn't double click before movement starts
			AbstractScript.sleepWhile(new Condition() {
				
				@Override
				public boolean verify() {
					return script.getClient().getLocalPlayer().isMoving();
				}
			}, Calculations.random(9000, 11000));
			//temp world hop until we make a node for it
			if(!script.getLocalPlayer().isInCombat() && script.getSettings().getWorldHop()) {
				hoWoodcutter.sleep(5000);
				script.getWorldHopper().hopWorld(script.getWorlds().getRandomWorld(World -> World.isF2P() == true).getID());
			}
		}
	}
}
