package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree", 34),
    OAK("Oak logs", "Oak", 35),
    WILLOW("Willow logs", "Willow", 9),
    MAPLE("Maple logs", "Maple", 22),
    YEW("Yew logs", "Yew", 391),
    MAGIC("Magic logs", "Magic", 1175);

    private int price;
    private String logName;
    private String treeName;

    Trees(String logName, String treeName, int price) {
        this.logName = logName;
      	this.treeName = treeName;
      	this.price = price;
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