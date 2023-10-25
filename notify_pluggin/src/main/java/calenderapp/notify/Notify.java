package calenderapp.notify;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;

public class Notify implements CalenderPlugginInterface {

    CalenderAppAPI theAPI;
    @Override
    public void start(CalenderAppAPI theAPI) {
        this.theAPI = theAPI;
        System.out.println("Notify has started");
    }
}
