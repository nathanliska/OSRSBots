package hoWoodcutter.task;

import org.dreambot.api.methods.Calculations;

import hoWoodcutter.hoWoodcutter;

public abstract class Node {
	
	//Script instance to use inside of subclasses
	public hoWoodcutter script;
	
	//Node constructor takes the script argument
	public Node(hoWoodcutter script) {
		this.script = script;
	}
	
	//Delays node 100-500ms
	public int delay() {
		return (int)Calculations.random(100, 500);
	}
	
	//Return a priority integer (the higher the better)
	public int priority() {
		return 0;
	}
	
	public abstract String status();
	public abstract boolean validate();
	public abstract void execute();
}
