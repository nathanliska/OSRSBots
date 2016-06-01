package hoWoodcutter.task.chop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.cache.nodes.NodeWrapper;
import org.dreambot.api.wrappers.interactive.GameObject;

import com.sun.tracing.dtrace.ProviderAttributes;

import hoWoodcutter.hoWoodcutter;
import hoWoodcutter.task.Node;

public class Chop extends Node {

	private boolean firstRun = true;
	private ArrayList<GameObject> trees = new ArrayList<GameObject>();
	private GameObjects gos = script.getGameObjects();
	private Queue<GameObject> treeQueue = new LinkedList<GameObject>();
	private int treesInArea = 0;

	public Chop(hoWoodcutter script) {
		super(script);
	}

	@Override
	public String status() {
		return "Chopping down trees.";
	}

	@Override
	public boolean validate() {
		return (!script.getInventory().isFull()
				&& script.getSettings().getLocations().getTreeArea().getArea().contains(script.getLocalPlayer())
				&& !script.getLevelUp());
	}

	@Override
	public void execute() {
		/* THIS GETS ALL TREES IN THE AREA but includes all stumps b/c no difference between yew stump and oak stump
		 * 
		 * 
		 * 
		*/if (firstRun) {
			// need a while that loops the correct amount of times here
			GameObject tree;
			do {
				tree = gos.closest(gameObject -> (gameObject.getName()
						.equals(script.getSettings().getLocations().getTree().getTreeName())
						|| gameObject.getName().equals("Tree Stump"))
						&& script.getSettings().getLocations().getTreeArea().getArea().contains(gameObject)
						&& !trees.contains(gameObject));
				if (tree != null) {
					trees.add(tree);
				}
			} while (tree != null);
			AbstractScript.log(trees.size() + "");
			for(int i = 0; i < trees.size(); i++) {
				AbstractScript.log("" + trees.get(i).getTile());
			}
			firstRun = false;
		}

		GameObject tree = script.getGameObjects()
				.closest(
						gameObject -> gameObject != null
								&& gameObject.getName()
										.equals(script.getSettings().getLocations().getTree().getTreeName())
								&& script.getSettings().getLocations().getTreeArea().getArea().contains(gameObject)
								&& script.getMap().canReach(gameObject) && gameObject.hasAction("Chop down"));

		if (tree != null) {
			try {
				if (tree.interact("Chop down")) {
					AbstractScript.sleep(Calculations.random(1750, 2000));
					while (script.getLocalPlayer().isMoving()) {
						AbstractScript.sleep(Calculations.random(1750, 2000));
					}
					AbstractScript.sleepUntil(
							() -> !tree.exists() || script.getInventory().isFull()
									|| script.getLocalPlayer().isInCombat() || !script.getLocalPlayer().isAnimating(),
							Calculations.random(100000, 150000));
					AbstractScript.sleep(Calculations.random(250, 750));
					if(!tree.exists()) {
						//AbstractScript.log("Added a tree we just cut down to the respawn queue.");
						treeQueue.add(tree);
					}
				} else {
					script.getWalking().walk(script.getSettings().getLocations().getTreeArea().getArea().getCenter());
				}
			} catch (NullPointerException e) {
				// tree disappeared before I got there!
			}
		} else {
			//AbstractScript.log("trees size: " + trees.size() + "    trees queue: " + treeQueue.size());
			if (script.getSettings().getWorldHopIfNoTrees()) {
				script.setWorldHop(true);
			} else if (treeQueue.size() >= trees.size()) {
				while(treeQueue.size() > trees.size()) {
					treeQueue.remove(); //prevents buildup to like a queue of 5 then rapid clicking both locations
										//until its down to treeQueue.size() < trees.size()
				}
				//now the queue and the amount of trees should be the same
				//AbstractScript.log("Walking to next tree to respawn, probably.");
				script.getWalking().walk(treeQueue.remove().getSurroundingArea(1).getNearestTile(script.getLocalPlayer()));
			}
		}
	}
}
