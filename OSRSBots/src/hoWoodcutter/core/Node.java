package hoWoodcutter.core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

public abstract class Node {
	
	//Script instance to use inside of subclasses.
	public AbstractScript aScript;
	
	//Node constructor takes the script argument.
	public Node(AbstractScript script) {
		this.aScript = script;
	}
	
	//Delays node 100-500ms.
	public int delay() {
		return (int)Calculations.random(100, 500);
	}
	
	//Return a string 
	public String status() {
		return "";
	}
	
	public int priority() {
		return 0;
	}
	
	public abstract boolean validate();
	public abstract void execute();
}
