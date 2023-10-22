package calenderapp.calplugins;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.*;

import java.time.LocalDate;
import java.time.LocalTime;


public class Repeat implements CalenderPlugginInterface {

    CalenderAppAPI theAPI ;
    Event theEvent;

    @Override
    public void start(CalenderAppAPI theAPI) {
        this.theAPI = theAPI;
    }

    @CreateRepeatEvents(title = "null")
    public void makeEvents(String title){
//        Event theNewEvent = new Event(title);

//        theEvent = theNewEvent;

        theAPI.addEvent(theEvent);
    }




}
