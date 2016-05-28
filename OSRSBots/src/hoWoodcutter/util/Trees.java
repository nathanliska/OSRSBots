package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree", 34),
    OAK("Oak logs", "Oak", 35),
    WILLOW("Willow logs", "Willow", 9),
    MAPLE("Maple logs", "Maple", 22),
    YEW("Yew logs", "Yew", 391),
    MAGIC("Magic logs", "Magic", 1175);

    private String logName;
    private String treeName;
    private TreeAreas[] treeAreas;
    private int price;

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
}