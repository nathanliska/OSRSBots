package hoWoodcutter.task.bank;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.NPC;

import hoWoodcutter.core.Node;
import hoWoodcutter.core.Settings;

public class Bank extends Node {

	public Bank(AbstractScript script) {
		super(script);
	}

	@Override
	public String status() {
		return "Banking some wood.";
	}
	
	@Override
	public boolean validate() {
		return (script.getInventory().isFull() && Settings.location.getBankArea().getArea().contains(script.getLocalPlayer()));
	}

	@Override
	public void execute() {
		NPC banker = script.getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
		
		if(banker.interact("Bank")) {
			if(AbstractScript.sleepUntil(() -> script.getBank().isOpen(), Calculations.random(5000, 8000))) {
				if(script.getBank().depositAllExcept(item -> item != null && item.getName().contains("axe"))) {
					if(AbstractScript.sleepUntil(() -> !script.getInventory().isFull(), Calculations.random(5000, 8000))) {
						if(script.getBank().close());
							AbstractScript.sleepUntil(() -> !script.getBank().isOpen(), Calculations.random(5000, 8000));
					}
				}
			}
		}
	}
}
