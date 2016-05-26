package hoWoodcutter.core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

public abstract class Node {
	
	//Script instance to use inside of subclasses
	public AbstractScript script;
	public Settings settings;
	
	//Node constructor takes the script argument
	public Node(AbstractScript script, Settings settings) {
		this.script = script;
		this.settings = settings;
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
