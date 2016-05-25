package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "Heatslinger and Opoz", version = 1.0)
public class hoWoodcutter extends AbstractScript {
	
	private boolean shouldStart;

	@Override
	public void onStart() {
		log("Hello, you have started hoWoodcutter by Heatslinger and Opoz, enjoy!");
	}
	
	@Override
	public int onLoop() {
		if(shouldStart) {
			
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