package hoWoodcutter.task.bank;

import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Bank extends Node {

	public Bank(AbstractScript _script) {
		super(_script);
	}

	@Override
	public String status() {
		return "Banking";
	}
	
	@Override
	public boolean validate() {
		return (Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()) && script.getInventory().isFull());
	}

	@Override
	public void execute() {
		// BANK ALL ITEMS.
		
	}
}
