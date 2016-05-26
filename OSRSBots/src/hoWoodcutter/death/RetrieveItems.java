package hoWoodcutter.death;

import org.dreambot.api.wrappers.interactive.GameObject;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class RetrieveItems extends Node {
	
	public RetrieveItems(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Picking up items.";
	}

	@Override
	public boolean validate() {
		return (script.getLocalPlayer().getTile().equals(script.getDeathSpot()) && script.getDeathSpot() != null);
	}

	@Override
	public void execute() {
		GameObject item = script.getGameObjects().getTopObjectOnTile(script.getDeathSpot());
		if(script.getLocalPlayer().getTile().equals(script.getDeathSpot())) {
			item.interact("Pick up");
		}
		if(script.getGameObjects().getObjectsOnTile(script.getDeathSpot()).length == 0) {
			script.setDeathSpot(null);
		}
	}
}
