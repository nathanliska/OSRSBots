package hoWoodcutter.task.bank;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.core.Node;

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
		return (script.getInventory().isFull() && !script.getSettings().getLocations().getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		if(script.getWalking().walk(script.getSettings().getLocations().getBankArea().getArea().getRandomTile())) {
			AbstractScript.sleep(Calculations.random(450, 550)); //buffer so it doesn't double click before movement starts
			AbstractScript.sleepWhile(new Condition() {
				
				@Override
				public boolean verify() {
					return script.getClient().getLocalPlayer().isMoving();
				}
			}, Calculations.random(5000, 7000));
		}
	}

}