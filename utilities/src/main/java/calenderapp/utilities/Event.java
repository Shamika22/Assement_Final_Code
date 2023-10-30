package calenderapp.utilities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String title;
    private LocalTime startTime;
    private LocalDate startDate;

    private int duration = 0;

    private boolean allDay;
    private int repeat;

    //    contructor for all day events
    public Event(String title ,  LocalDate date , LocalTime time){
        this.title = title;
        this.startDate = date;
        this.startTime = time;
        this.allDay = true;
    }

    //    contructor for repetitive events
    public Event(String title , LocalDate date , LocalTime time , int duration , int repeat) {
        this.title = title;
        this.startTime = time;
        this.startDate = date;
        this.duration = duration;
        this.repeat = repeat;
        this.allDay = false;

    }
//    contructor for normal events
    public Event(String title , LocalDate date , LocalTime time , int duration ) {
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

    public int getDuration() {
        return duration;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "The event is"+ this.title + "start date is" + this.startDate + "start time is"+this.startTime;
    }
}
