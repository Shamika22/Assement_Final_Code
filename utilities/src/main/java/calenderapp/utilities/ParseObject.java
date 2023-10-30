package calenderapp.utilities;

import java.util.ArrayList;
import java.util.List;

public class ParseObject {
    private List<Event> eventList = new ArrayList<>();
    private List<PlugginParser> plugginParserList = new ArrayList<>();


    public List<Event> getEventList() {
        return eventList;
    }
    public List<PlugginParser> getPlugginList(){return plugginParserList;}
    public void addEvent (Event inEvent){
        eventList.add(inEvent);
    }
    public void addPluggin (PlugginParser plugginIn){
        plugginParserList.add(plugginIn);
    }



}
