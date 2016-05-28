package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum TreeAreas {

	TREE_DRAYNOR_NORTH("North Draynor", new Area(3092, 3288, 3105, 3283, 0), new Trees[]{Trees.NORMAL, Trees.OAK}, new BankAreas[]{BankAreas.BANK_DRAYNOR}),
	TREE_DRAYNOR_SE("South-East Draynor", new Area(3118, 3233, 3096, 3215, 0), new Trees[]{Trees.NORMAL, Trees.OAK, Trees.WILLOW}, new BankAreas[]{BankAreas.BANK_DRAYNOR, BankAreas.BANK_VARROCK_EAST});
	
	private final String areaString;
	private final Area area;
	private final Trees[] areaTrees;
	private final BankAreas[] bankAreas;
	
	TreeAreas(String areaString, Area area, Trees[] areaTrees, BankAreas[] bankAreas) {
		this.areaString = areaString;
		this.area = area;
		this.areaTrees = areaTrees;
		this.bankAreas = bankAreas;
	}
	
	public String getAreaName() {
		return areaString;
	}
	
	public Area getArea() {
		return area;
	}
	
	public Trees[] getTreeAreas() {
		return areaTrees;
	}
	
	public BankAreas[] getBankAreas() {
		return bankAreas;
	}
	
	public boolean getAreaContains(Trees tree) {
		for (int i = 0; i < areaTrees.length; i++) {
			if (tree.equals(areaTrees[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean getAreaContains(BankAreas bankArea) {
		for (int i = 0; i < bankAreas.length; i++) {
			if (bankArea.equals(bankAreas[i])) {
				return true;
			}
		}
		return false;
	}
	
	public String[] getBankAreasStrings() {
		String[] temp = new String[getBankAreas().length];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = getBankAreas()[i].getAreaName();
		}
		return temp;
	}
}