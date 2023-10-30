package calenderapp.notify;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.Message;


import java.util.Map;

public class Notify implements CalenderPlugginInterface {
    @SuppressWarnings({"PMD. UnusedPrivateField","PMD.SingularField" })
    /*
    * I got this warnig because the variable theAPI which stores the instace of the main app api to communicae back with the main application is nit currelty beig used here but int he assingment spec there is a clear need that there need to be a mechanics to communciate to the main application by the pluggins so this varibale is needed within the notify instance eventhrogh at the current scope this is not used.
    *
    * */
    private CalenderAppAPI theAPI;
    private String theReminderEvent;

    @Override
    public void start(CalenderAppAPI theAPIIn, Map<String, String> argumentList) {
        this.theAPI = theAPIIn;
        if(argumentList.containsKey("text")){
            this.theReminderEvent = argumentList.get("text");
        }

    }

    @Override
    public void notify(Message theMessage) {
        if(theMessage.getTheStartEvent().getTitle().equals(theReminderEvent)){
            System.out.println("***AN EVENT HAS STARTED***");
            System.out.println(theMessage.getTheStartEvent());
        }
    }
}
