package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum TreeAreas {
	
	TREE_VARROCK_EAST(new Area()),
	TREE_VARROCK_WEST(new Area());
	
	private Area area;
	
	TreeAreas(Area _area) {
		this.area = _area;
	}
	
	public Area getArea() {
		return area;
	}

}