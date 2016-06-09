package hoWoodcutter.task.antiban;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class Antiban extends Node {

	public Antiban(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Doing antiban stuff";
	}
	
	@Override
	public boolean validate() {
		return script.getAntiban();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}
}
