package hoWoodcutter.task.chop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class ChopWalk extends Node {

	public ChopWalk(AbstractScript script, Settings settings) {
		super(script, settings);
	}

	@Override
	public String status() {
		return "Walking to trees.";
	}
	
	@Override
	public boolean validate() {
		return (!script.getInventory().isFull() && !settings.getLocation().getTreeArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		if(script.getWalking().walk(settings.getLocation().getTreeArea().getArea().getRandomTile())) {
			AbstractScript.sleep(Calculations.random(450, 550)); //buffer so it doesn't double click before movement starts
			AbstractScript.sleepWhile(new Condition() {
				
				@Override
				public boolean verify() {
					return script.getLocalPlayer().isMoving();
				}
			}, Calculations.random(5000, 7000));
		}
		
	}

}
