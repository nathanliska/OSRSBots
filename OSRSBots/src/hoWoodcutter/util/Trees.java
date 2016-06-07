package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree", 1511),
    OAK("Oak logs", "Oak", 1521),
    WILLOW("Willow logs", "Willow", 1519),
    MAPLE("Maple logs", "Maple", 1517),
    YEW("Yew logs", "Yew", 1515),
    MAGIC("Magic logs", "Magic", 1513);

    private final String logName;
    private final String treeName;
    private final int id;
    
    Trees(String logName, String treeName, int id) {
        this.logName = logName;
      	this.treeName = treeName;
      	this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public String getTreeName() {
        return treeName;
    }
    
    public int getLogID() {
    	return id;
    }
}