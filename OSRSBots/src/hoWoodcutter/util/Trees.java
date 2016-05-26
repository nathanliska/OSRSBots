package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree"),
    OAK("Oak logs", "Oak"),
    WILLOW("Willow logs", "Willow"),
    MAPLE("Maple logs", "Maple"),
    YEW("Yew logs", "Yew"),
    MAGIC("Magic logs", "Magic");

    private String logName;
    private String treeName;

    Trees(String logName, String treeName) {
        this.logName = logName;
      	this.treeName = treeName;
    }

    public String getLogName() {
        return logName;
    }

    public String getTreeName() {
        return treeName;
    }
}