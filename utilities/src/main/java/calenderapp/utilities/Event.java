package calenderapp.utilities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String title;
    private LocalTime startTime;
    private LocalDate startDate;

    private int duration;

    private boolean allDay;

    public Event(String title ,  LocalDate date){
        this.title = title;
        this.startDate = date;
        this.allDay = true;
    }
    public Event(String title , LocalDate date , LocalTime time , int duration) {
        this.title = title;
        this.startTime = time;
        this.startDate = date;
        this.duration = duration;
        this.allDay = false;
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

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Override
    public String toString() {
        return "The event is"+ this.title + "start date is" + this.startDate + "start time is"+this.startTime;
    }
}
