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

import java.util.Map;

public class Repeat implements CalenderPlugginInterface {
    private CalenderAppAPI theAPI;
    private List<Event> theRepeatEventList = new ArrayList<>();

    public void makeEvents(Map<String, String>argumentMap) {
//        TODO:All dat but repetive events are not handled yet
        if(argumentMap.containsKey("title") && argumentMap.containsKey("startDate") && argumentMap.containsKey("startTime") && argumentMap.containsKey("duration") && argumentMap.containsKey("repeat")){

            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate localDate = LocalDate.parse(argumentMap.get("startDate"), dateFormatter);
                LocalTime localTime = LocalTime.parse(argumentMap.get("startTime"), timeFormatter);

                LocalDate theRepeatEndDate = localDate.plusYears(1);
                LocalDate theCurrentDate = localDate;
//        creating repeat events
                while (theCurrentDate.isBefore(theRepeatEndDate)) {
                    Event theEvent = new Event(argumentMap.get("title"),theCurrentDate,localTime,Integer.parseInt(argumentMap.get("duration")) ,Integer.parseInt(argumentMap.get("repeat")) );
                    theRepeatEventList.add(theEvent);
                    theCurrentDate = theCurrentDate.plusDays(Integer.parseInt(argumentMap.get("repeat")));
                }

            }catch (DateTimeParseException | NumberFormatException e){
                System.out.println(" *****WARNINGE***** Error while loading the repeat pluggin data the error is" + e);
            }


        } else if(argumentMap.containsKey("title") && argumentMap.containsKey("startDate") &&  argumentMap.containsKey("repeat") ){
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime = LocalTime.parse("00:00:00", timeFormatter);
                LocalDate localDate = LocalDate.parse(argumentMap.get("startDate"), dateFormatter);

                LocalDate theRepeatEndDate = localDate.plusYears(1);
                LocalDate theCurrentDate = localDate;
//        creating repeat events
                while (theCurrentDate.isBefore(theRepeatEndDate)) {
                    Event theEvent = new Event(argumentMap.get("title"),theCurrentDate , localTime );
                    theEvent.setRepeat(Integer.parseInt(argumentMap.get("repeat")));
                    theRepeatEventList.add(theEvent);
                    theCurrentDate = theCurrentDate.plusDays(Integer.parseInt(argumentMap.get("repeat")));
                }

            }catch (DateTimeParseException e){
                System.out.println(" *****WARNINGE***** Error while loading the repeat pluggin data the error is" + e);
            }catch (NumberFormatException e){
                System.out.println(" *****WARNINGE*****  Error while loading the repeat pluggin data the error is" + e);
            }

        } else {
            System.out.println("Not all arguments are present to make a repeat event");
        }


        theAPI.addEvent(theRepeatEventList);
    }

    @Override
    public void start(CalenderAppAPI theAPI , Map<String, String>argumentMap) {
        this.theAPI = theAPI;

        makeEvents(argumentMap);
    }

    @Override
    public void notify(Message theMessage) {

    }
}
