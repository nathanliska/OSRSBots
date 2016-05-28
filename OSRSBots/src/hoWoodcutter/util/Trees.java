package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree", 34, new TreeAreas[] {TreeAreas.TREE_DRAYNOR_NORTH, TreeAreas.TREE_DRAYNOR_SE}),
    OAK("Oak logs", "Oak", 35, new TreeAreas[] {TreeAreas.TREE_DRAYNOR_NORTH, TreeAreas.TREE_DRAYNOR_SE}),
    WILLOW("Willow logs", "Willow", 9),
    MAPLE("Maple logs", "Maple", 22),
    YEW("Yew logs", "Yew", 391),
    MAGIC("Magic logs", "Magic", 1175);

    private int price;
    private String logName;
    private String treeName;
    private TreeAreas[] treeAreas;

    Trees(String logName, String treeName, int price) {
        this.logName = logName;
      	this.treeName = treeName;
      	this.price = price;
      	this.treeAreas = null;
    }
    
    Trees(String logName, String treeName, int price, TreeAreas[] treeAreas) {
        this.logName = logName;
      	this.treeName = treeName;
      	this.price = price;
      	this.treeAreas = treeAreas;
    }

    public String getLogName() {
        return logName;
    }

    public String getTreeName() {
        return treeName;
    }
    
    public int getLogPrice() {
    	return price;
    }
    
    public TreeAreas[] getTreeAreas() {
		return treeAreas;
    }
    
    public String[] getTreeAreasStrings() {
    	String[] temp;
    	if(treeAreas != null) {
    		temp = new String[treeAreas.length];
    	} else {
    		return new String[] {""};
    	}
    	
    	for(int i = 0; i < temp.length; i++) {
			temp[i] = getTreeAreas()[i].getAreaName();
		}
		return temp;
    }
}