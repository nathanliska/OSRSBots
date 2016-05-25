package hoWoodcutter;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "Opoz & Heatslinger", version = 1.0)
public class Main extends AbstractScript
{
	
	@Override
	public void onStart()
	{
		log("Hello, you have started hoWoodcutter by Opoz and HeatSlinger, enjoy!");
	}
	
	@Override
	public int onLoop()
	{
		// TODO Auto-generated method stub
		return Calculations.random(300, 500);
	}
	
	@Override
	public void onExit()
	{
		log("Goodbye!");
	}

}
