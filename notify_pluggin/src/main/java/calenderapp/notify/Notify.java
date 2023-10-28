package calenderapp.notify;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;

import java.util.Map;

public class Notify implements CalenderPlugginInterface {

    CalenderAppAPI theAPI;

    @Override
    public void start(CalenderAppAPI theAPI, Map<String, String> argumentList) {
        this.theAPI = theAPI;
        System.out.println("Notify has started");
    }
}
