package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum TreeAreas {
	
	TREE_VARROCK_EAST("Varrock East", new Area()),
	TREE_VARROCK_WEST("Varrock West", new Area());
	
	private String stringArea;
	private Area area;
	
	TreeAreas(String _stringArea, Area _area) {
		this.stringArea = _stringArea;
		this.area = _area;
	}
	
	public String getStringArea() {
		return stringArea;
	}
	
	public Area getArea() {
		return new Area(3076, 3253, 3078, 3251, 0);
	}

}