package calenderapp.api;

import calenderapp.utilities.Message;

import java.util.Map;

public interface CalenderPlugginInterface {
    void start(CalenderAppAPI theAPI , Map<String, String> argumentList);
    void notify(Message theMessage);
}
