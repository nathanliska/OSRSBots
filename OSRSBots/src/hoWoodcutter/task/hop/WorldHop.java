package hoWoodcutter.task.hop;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class WorldHop extends Node {

	public WorldHop(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Hopping worlds.";
	}

	@Override
	public boolean validate() {
		return (!script.getLocalPlayer().isInCombat() && script.getWorldHop());
	}

	@Override
	public void execute() {
		AbstractScript.sleep(1000, 2000);
		if (script.getClient().getMembershipLeft() < 0) {
			// hop to f2p
			if (script.getWorldHopper()
					.hopWorld(script.getWorlds().getRandomWorld(World -> World.isF2P() == true).getID())) {
				script.setWorldHop(false);
			}
		} else {
			// hop to members
			if (script.getWorldHopper()
					.hopWorld(script.getWorlds().getRandomWorld(World -> World.isMembers() == true).getID())) {
				script.setWorldHop(false);
			}
		}
		AbstractScript.sleep(Calculations.random(3000, 4000));
	}
}