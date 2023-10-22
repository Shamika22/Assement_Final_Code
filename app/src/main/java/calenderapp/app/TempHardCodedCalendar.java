package calenderapp.app;

import calenderapp.utilities.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class TempHardCodedCalendar {
    String [] pluginNames = {"calenderapp.calplugins.Repeat" , "calenderapp.calplugins.Notify"};


    //Initiating events
    Event eventOne = new Event("Event one" , LocalDate.now() , LocalTime.now());
    Event eventTwo = new Event("Event Two" , LocalDate.now().plusDays(1) , LocalTime.now());
    Event eventThree = new Event("Event Three" , LocalDate.now().plusDays(5)  , LocalTime.now());
    Event eventFour = new Event("Event Four" , LocalDate.now().plusDays(10)  , LocalTime.now());
    Event eventFive = new Event("Event Five" , LocalDate.now().plusDays(11)  , LocalTime.now());
    Event eventSix = new Event("Event Six" , LocalDate.now().plusDays(20)  , LocalTime.now());
    Event eventSeven = new Event("Event Seven" , LocalDate.now().plusDays(3)  , LocalTime.now());
    Event eventEight = new Event("Event Eight" , LocalDate.now().plusDays(5)  , LocalTime.now());

    Event [] eventArray = {eventOne,eventTwo,eventThree,eventFour,eventFive,eventSix,eventSeven,eventEight};

    public String[] getPluginNames() {
        return pluginNames;
    }

    public void setPluginNames(String[] pluginNames) {
        this.pluginNames = pluginNames;
    }

    public Event[] getEventArray() {
        return eventArray;
    }
}
