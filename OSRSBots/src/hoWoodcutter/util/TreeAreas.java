package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum TreeAreas {
	
	TREE_VARROCK_EAST("Varrock East", new Area(), new Trees[]{Trees.NORMAL, Trees.OAK}),
	TREE_VARROCK_WEST("Varrock West", new Area(), new Trees[]{Trees.NORMAL, Trees.OAK}),
	TREE_DRAYNOR_NORTH("North Draynor", new Area(3092, 3288, 3105, 3283, 0), new Trees[]{Trees.NORMAL, Trees.OAK, Trees.MAGIC}),
	TREE_DRAYNOR_SE("South-East Draynor", new Area(3118, 3233, 3096, 3215, 0), new Trees[]{Trees.NORMAL, Trees.OAK});
	
	private String areaString;
	private Area area;
	private Trees[] areaTrees;

	TreeAreas(String areaString, Area area) {
		this.areaString = areaString;
		this.area = area;
		this.areaTrees = null;
	}
	
	TreeAreas(String areaString, Area area, Trees[] areaTrees) {
		this.areaString = areaString;
		this.area = area;
		this.areaTrees = areaTrees;
	}
	
	public String getAreaName() {
		return areaString;
	}
	
	public Area getArea() {
		return area;
	}
	
	public Trees[] getAreaTrees() {
		return areaTrees;
	}
	
	public boolean getAreaContains(Trees tree) {
		for (int i = 0; i < areaTrees.length; i++) {
			if (tree.equals(areaTrees[i])) {
				return true;
			}
		}
		return false;
	}
	
	public String[] getAreaTreesStrings() {
		String[] temp = new String[getAreaTrees().length];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = getAreaTrees()[i].getTreeName();
		}
		return temp;
	}
	
}