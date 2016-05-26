package hoWoodcutter.task.bank;

import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class BankWalk extends Node {

	public BankWalk(AbstractScript _script) {
		super(_script);
	}

	@Override
	public String status() {
		return "Walking to bank.";
	}
	
	@Override
	public boolean validate() {
		return (script.getInventory().isFull() && !Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		// TODO WALK TO BANK AREA.
		
	}

}