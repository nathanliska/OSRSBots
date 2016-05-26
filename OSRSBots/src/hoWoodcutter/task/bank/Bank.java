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
		return "Banking.";
	}
	
	@Override
	public boolean validate() {
		return (script.getInventory().isFull() && Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		// TODO BANK ALL ITEMS IN BANK AREA.
		
	}
}
