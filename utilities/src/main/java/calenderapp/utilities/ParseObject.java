package calenderapp.utilities;

import java.util.ArrayList;
import java.util.List;

public class ParseObject {
    private List<Event> EventList = new ArrayList<>();
    private List<PlugginParser> plugginParserList = new ArrayList<>();


    public List<Event> getEventList() {
        return EventList;
    }
    public List<PlugginParser> getPlugginList(){return plugginParserList;}
    public void addEvent (Event inEvent){
        EventList.add(inEvent);
    }
    public void addPluggin (PlugginParser plugginIn){
        plugginParserList.add(plugginIn);
    }



}
