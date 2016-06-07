package hoWoodcutter.task;

import org.dreambot.api.methods.Calculations;

import hoWoodcutter.hoWoodcutter;

public abstract class Node {
	
	//Script instance to use inside of subclasses
	public hoWoodcutter script;
	public static boolean random;
	
	//Node constructor takes the script argument
	public Node(hoWoodcutter script) {
		this.script = script;
	}
	
	//Sets random to true for a given time.
	public static void setRandomTrue(int time) {
		random = true;
	}
	
	//Delays node 100-500ms
	public int delay() {
		return (int)Calculations.random(100, 500);
	}
	
	//Return a string
	public String status() {
		return "";
	}
	
	//Return a priority integer (the higher the better)
	public int priority() {
		return 0;
	}
	
	public abstract boolean validate();
	public abstract void execute();
}
