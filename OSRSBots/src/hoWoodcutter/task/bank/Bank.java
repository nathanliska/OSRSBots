package hoWoodcutter.task.bank;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.NPC;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class Bank extends Node {

	public Bank(hoWoodcutter script) {
		super(script);
	}

	/**
	 * @return banking status
	 */
	@Override
	public String status() {
		return "Banking some wood.";
	}

	/**
	 * Checks if execute() should execute - 
	 * Requires:
	 * inventory to have room,
	 * be in bank area,
	 * not be in the process of handling a level up
	 * @return whether execute() should execute
	 */
	@Override
	public boolean validate() {
		return (script.getInventory().isFull()
				&& script.getSettings().getLocations().getBankArea().getArea().contains(script.getLocalPlayer())
				&& !script.getLevelUp());
	}

	/**
	 * Banks all non-axe items
	 */
	@Override
	public void execute() {
		NPC banker = script.getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));

		if (banker.interact("Bank")) {
			if (AbstractScript.sleepUntil(() -> script.getBank().isOpen(), Calculations.random(5000, 8000))) {
				if (script.getBank().depositAllExcept(item -> item != null && item.getName().contains("axe"))) {
					if (AbstractScript.sleepUntil(() -> !script.getInventory().isFull(),
							Calculations.random(5000, 8000))) {
						if (script.getBank().close()) {
							AbstractScript.sleepUntil(() -> !script.getBank().isOpen(), Calculations.random(5000, 8000));
						}
					}
				}
			}
		}
	}
}
