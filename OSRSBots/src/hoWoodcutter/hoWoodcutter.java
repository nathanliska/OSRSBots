package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.core.Settings;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 1.0)
public class hoWoodcutter extends AbstractScript {
	
	private boolean shouldStart;
	private Settings settings;
	
	@Override
	public void onStart() {
		log("Hello, you have started hoWoodcutter by HeatSlinger & Opoz, enjoy!");
		
		settings = new Settings();
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