package calenderapp.utilities;

public class Message {
   private Event theStartEvent;

    public Message(Event theStartEvent) {
        this.theStartEvent = theStartEvent;
    }

    public Event getTheStartEvent() {
        return theStartEvent;
    }

    public void setTheStartEvent(Event theStartEvent) {
        this.theStartEvent = theStartEvent;
    }
}
