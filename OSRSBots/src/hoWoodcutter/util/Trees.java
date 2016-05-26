package hoWoodcutter.util;

public enum Trees {
    NORMAL("Logs", "Tree"),
    OAK("Oak logs", "Oak tree"),
    WILLOW("Willow logs", "Willow tree"),
    MAPLE("Maple logs", "Maple tree"),
    YEW("Yew logs", "Yew tree"),
    MAGIC("Magic logs", "Magic tree");

    private String logName;
    private String treeName;

    Trees(String _logName, String _treeName) {
        this.logName = _logName;
      	this.treeName = _treeName;
    }

    public String getLogName() {
        return logName;
    }

    public String getTreeName() {
        return treeName;
    }
}