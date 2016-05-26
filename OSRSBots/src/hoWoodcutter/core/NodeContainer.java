package hoWoodcutter.core;

import org.dreambot.api.script.AbstractScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class NodeContainer implements Comparator<Node> {
	
	public List<Node> nodeList = new ArrayList<Node>();
	public AbstractScript script;
	
	public void submit(final Node... workers) {
		for (Node w : workers) {
			if (!nodeList.contains(w)) {
				nodeList.add(w);
			}
		}
		Collections.sort(nodeList, this);
	}
	
	@Override
	public int compare(Node n1, Node n2) {
		return n2.priority() - n1.priority();
	}
	
	public void clear() {
		nodeList.clear();
	}
	
	public Node get() {
		for (Node n : nodeList) {
			if (n.validate()) {
				return n;
			}
		}
		
		return null;
	}
	
	public abstract boolean activate();
}
