package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 0.1)
public class hoWoodcutter extends AbstractScript {
	
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
			GameObject tree = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals("Tree")
					&& gameObject.hasAction("Chop down"));
			
			if(getInventory().isFull()) { //If inventory is full, bank
				Bank();
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
	
	public void Bank() {
		
	}
	
	public void Chop() {
		
	}

}