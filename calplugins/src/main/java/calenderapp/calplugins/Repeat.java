package calenderapp.calplugins;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Repeat implements CalenderPlugginInterface {

    CalenderAppAPI theAPI;
    List<Event> theRepeatEventList = new ArrayList<>();

    @Override
    public void start(CalenderAppAPI theAPI) {
        this.theAPI = theAPI;
    }

    @CreateRepeatEvents(title = "null")
    public void makeEvents(String title, LocalDate date, LocalTime time, int duration) {

        LocalDate theRepeatEndDate = date.plusYears(1);
        LocalDate theCurrentDate = date;
//        creaging repeat events
        while (theCurrentDate.isBefore(theRepeatEndDate)) {
            Event event = new Event(title, theCurrentDate, time);
            theRepeatEventList.add(event);
            theCurrentDate = theCurrentDate.plusDays(duration);

        }
        theAPI.addEvent(theRepeatEventList);
    }

}
