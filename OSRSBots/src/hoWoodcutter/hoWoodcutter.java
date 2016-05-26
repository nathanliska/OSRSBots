package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import hoWoodcutter.core.Node;
import hoWoodcutter.task.bank.Bank;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 1.0)
public class hoWoodcutter extends AbstractScript {
	
	private final Node[] nodeArray = new Node[] {new Bank(this)};
	
	private boolean shouldStart;
	private hoWoodcutterGUI gui;
	
	@Override
	public void onStart() {
		gui = new hoWoodcutterGUI(this);
		gui.setVisible(true);
		log("Hello, you have started hoWoodcutter by HeatSlinger & Opoz, enjoy!");
		
		
	}
	
	@Override
	public int onLoop() {
		if(shouldStart) {
			for (final Node node : nodeArray) {
				if (node.validate()) {
					node.execute();
				}
			}
		}
		return Calculations.random(300, 500);
	}
	
	@Override
	public void onExit() {
		log("Goodbye!");
	}
	
	public void setShouldStart(boolean shouldStart) {
		this.shouldStart = shouldStart;
	}
}