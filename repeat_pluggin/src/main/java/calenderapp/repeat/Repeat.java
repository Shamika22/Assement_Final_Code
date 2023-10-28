package calenderapp.repeat;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Map;

public class Repeat implements CalenderPlugginInterface {
    CalenderAppAPI theAPI;
    List<Event> theRepeatEventList = new ArrayList<>();

    public void makeEvents(Map<String, String>argumentMap) {
//        TODO:All dat but repetive events are not handled yet
        if(argumentMap.containsKey("title") && argumentMap.containsKey("StartDate") && argumentMap.containsKey("StartTime") && argumentMap.containsKey("duration") && argumentMap.containsKey("repeat")){
            System.out.println(argumentMap.containsKey("title"));
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate localDate = LocalDate.parse(argumentMap.get("StartDate"), dateFormatter);
                LocalTime localTime = LocalTime.parse(argumentMap.get("StartTime"), timeFormatter);

                LocalDate theRepeatEndDate = localDate.plusYears(1);
                LocalDate theCurrentDate = localDate;
//        creating repeat events
                while (theCurrentDate.isBefore(theRepeatEndDate)) {
                    Event theEvent = new Event(argumentMap.get("title"),theCurrentDate,localTime,Integer.parseInt(argumentMap.get("duration")) ,Integer.parseInt(argumentMap.get("repeat")) );
                    theRepeatEventList.add(theEvent);
                    theCurrentDate = theCurrentDate.plusDays(Integer.parseInt(argumentMap.get("repeat")));
                }

            }catch (DateTimeParseException e){
                System.out.println(e);
            }catch (Exception e){
                System.out.println("Error occured while reading arguments from argument map");
            }



        } else if(argumentMap.containsKey("title") && argumentMap.containsKey("StartDate") &&  argumentMap.containsKey("repeat") ){
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(argumentMap.get("StartDate"), dateFormatter);

                LocalDate theRepeatEndDate = localDate.plusYears(1);
                LocalDate theCurrentDate = localDate;
//        creating repeat events
                while (theCurrentDate.isBefore(theRepeatEndDate)) {
                    Event theEvent = new Event(argumentMap.get("title"),theCurrentDate );
                    theEvent.setRepeat(Integer.parseInt(argumentMap.get("repeat")));
                    theRepeatEventList.add(theEvent);
                    theCurrentDate = theCurrentDate.plusDays(Integer.parseInt(argumentMap.get("repeat")));
                }

            }catch (DateTimeParseException e){
                System.out.println(e);
            }catch (Exception e){
                System.out.println("Error occured while reading arguments from argument map");
            }

        } else {
            System.out.println("Not all arguments are present to make a repeat event");
        }

        System.out.println("Adding repeat events");
        theAPI.addEvent(theRepeatEventList);
    }

    @Override
    public void start(CalenderAppAPI theAPI , Map<String, String>argumentMap) {
        this.theAPI = theAPI;
        System.out.println("Repeat plugin has started");
        makeEvents(argumentMap);
    }
}
