package hoWoodcutter.task.powerChop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class DropAll extends Node {

	public DropAll(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Chopping down trees.";
	}

	@Override
	public boolean validate() {
		return (script.getInventory().isFull() && script.getSettings().getPowerChop() && !script.getLevelUp());
	}

	@Override
	public void execute() {
		if (script.getInventory().dropAllExcept(item -> item != null && item.getName().contains("axe"))) {
			AbstractScript.sleepUntil(() -> 
					script.getInventory().onlyContains(item -> item != null && item.getName().contains("axe")),
					Calculations.random(20000, 30000));
		}
	}
}
