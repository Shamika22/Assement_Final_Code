package calenderapp.utilities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String title;
    private LocalTime startTime;
    private LocalDate startDate;

    public Event(String title , LocalDate date , LocalTime time) {
        this.title = title;
        this.startTime = time;
        this.startDate = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "The event is"+ this.title + "start date is" + this.startDate + "start time is"+this.startTime;
    }
}
