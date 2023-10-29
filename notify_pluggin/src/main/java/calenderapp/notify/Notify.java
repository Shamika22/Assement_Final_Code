package calenderapp.notify;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.Message;

import java.util.Map;

public class Notify implements CalenderPlugginInterface {

    private CalenderAppAPI theAPI;
    private String theReminderEvent;

    @Override
    public void start(CalenderAppAPI theAPI, Map<String, String> argumentList) {
        this.theAPI = theAPI;
        if(argumentList.containsKey("text")){
            this.theReminderEvent = argumentList.get("text");
        }

    }

    @Override
    public void notify(Message theMessage) {
        if(theMessage.getTheStartEvent().getTitle().equals(theReminderEvent)){

            System.out.println(theMessage.getTheStartEvent());
        }
    }
}
