package hoWoodcutter.task.hop;

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
		script.setWorldHop(false);
		if (script.getClient().getMembershipLeft() < 0) {
			// hop to f2p
			script.getWorldHopper().hopWorld(script.getWorlds().getRandomWorld(World -> World.isF2P() == true).getID());
		} else {
			// hop to members
			script.getWorldHopper()
					.hopWorld(script.getWorlds().getRandomWorld(World -> World.isMembers() == true).getID());
		}
	}
}