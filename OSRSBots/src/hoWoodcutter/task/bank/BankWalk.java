package hoWoodcutter.task.bank;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class BankWalk extends Node {

	public BankWalk(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Walking to bank.";
	}

	@Override
	public boolean validate() {
		return (script.getInventory().isFull()
				&& !script.getSettings().getLocations().getBankArea().getArea().contains(script.getLocalPlayer())
				&& !script.getLevelUp());
	}

	@Override
	public void execute() {
		if (script.getWalking().walk(script.getSettings().getLocations().getBankArea().getArea().getRandomTile())) {
			AbstractScript.sleep(Calculations.random(650, 850));
			// buffer so it doesn't double click before movement starts
			AbstractScript.sleepUntil(() -> !script.getLocalPlayer().isMoving(), Calculations.random(3000, 5000));
		}
	}
}