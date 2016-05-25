package hoWoodcutter.task.bank;

import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.core.Node;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
