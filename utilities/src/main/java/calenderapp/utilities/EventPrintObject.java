package calenderapp.utilities;

public class EventPrintObject {
    private Event theEvent;
    private int xVal;
    private int yVal;

    public EventPrintObject(Event theEvent, int xVal, int yVal) {
        this.theEvent = theEvent;
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public Event getTheEvent() {
        return theEvent;
    }

    public void setTheEvent(Event theEvent) {
        this.theEvent = theEvent;
    }

    public int getxVal() {
        return xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }
}
