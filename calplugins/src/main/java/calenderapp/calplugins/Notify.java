package calenderapp.calplugins;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;

public class Notify implements CalenderPlugginInterface{
    @Override
    public void start(CalenderAppAPI theAPI) {
        System.out.println("Printing from Notofy pluggin");
    }
}
