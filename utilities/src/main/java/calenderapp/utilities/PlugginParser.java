package calenderapp.utilities;

import java.util.HashMap;
import java.util.Map;

public class PlugginParser {
    private String plugginID;
    private Map<String, String> argumentMap = new HashMap<>();

    public PlugginParser(String plugginID) {
        this.plugginID = plugginID;
    }

    public String getPlugginID() {
        return plugginID;
    }

    public void setPlugginID(String plugginID) {
        this.plugginID = plugginID;
    }

    public Map<String, String> getArgumentMap() {
        return argumentMap;
    }

    public void setArgumentMap(Map<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    public void addArguments(String argumentKey , String argumentValue){
        argumentMap.put(argumentKey,argumentValue);
    }
}
