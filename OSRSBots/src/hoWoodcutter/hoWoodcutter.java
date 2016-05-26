package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import hoWoodcutter.core.Node;
import hoWoodcutter.task.bank.Bank;
import hoWoodcutter.task.bank.BankWalk;
import hoWoodcutter.task.chop.Chop;
import hoWoodcutter.task.chop.ChopWalk;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 0.1)
public class hoWoodcutter extends AbstractScript {
	
	private final Node[] nodeArray = new Node[] {new Bank(this), new BankWalk(this), new Chop(this), new ChopWalk(this)};
	
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