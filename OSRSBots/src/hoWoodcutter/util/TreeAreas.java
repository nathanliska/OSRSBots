package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum TreeAreas {
	
	TREE_VARROCK_EAST("Varrock East", new Area()),
	TREE_VARROCK_WEST("Varrock West", new Area()),
	TREE_DRAYNOR_NORTH("North of Draynor", new Area(3092, 3288, 3105, 3283, 0)),
	TREE_DRAYNOR_SE("South-East of Draynor", new Area(3118, 3233, 3096, 3215, 0));
	
	private String stringArea;
	private Area area;
	
	TreeAreas(String stringArea, Area area) {
		this.stringArea = stringArea;
		this.area = area;
	}
	
	public String getStringArea() {
		return stringArea;
	}
	
	public Area getArea() {
		return area;
	}

}