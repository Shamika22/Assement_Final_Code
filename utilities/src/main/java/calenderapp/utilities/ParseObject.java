package calenderapp.utilities;

import java.util.ArrayList;
import java.util.List;

public class ParseObject {
    private List<Event> EventList = new ArrayList<>();


    public List<Event> getEventList() {
        return EventList;
    }

    public void setEventList(List<Event> eventList) {
        EventList = eventList;
    }

    public void addEvent (Event inEvent){
        EventList.add(inEvent);
    }

}
