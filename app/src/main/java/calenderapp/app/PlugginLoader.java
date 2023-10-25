package calenderapp.app;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.CreateRepeatEvents;
import calenderapp.utilities.Event;
import calenderapp.utilities.ParseObject;
import calenderapp.utilities.PlugginParser;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PlugginLoader {
    List<Event> theMainEventlist = new ArrayList<>();
    List<PlugginParser> thePlugginList;

    public void getEvents() {

        try {
            InputStream inputStream = MyParser.class.getResourceAsStream("/" + "input.txt");
            MyParser theParser = new MyParser(inputStream);
            ParseObject theParsedObjectList = theParser.parse("input.txt");

            theMainEventlist = theParsedObjectList.getEventList();
            thePlugginList = theParsedObjectList.getPlugginList();


        } catch (Exception e) {
            System.out.println(e);
        }

        loadPluggins();

    }

    public void loadPluggins() {


//        Intialize the Calender API
        CalenderAppAPI theMainAPI = new CalenderAppAPI() {

            @Override
            public void printVal(String theVal) {

            }

            @Override
            public void addEvent(List<Event> theEventLisIn) {
                theMainEventlist.addAll(theEventLisIn);
            }
        };
        try {
            for (PlugginParser thePluginParser : thePlugginList) {

                System.out.println(thePluginParser.getPlugginID());

                Class<?> theRunTimeClass = Class.forName(thePluginParser.getPlugginID());
                CalenderPlugginInterface thePluggin = (CalenderPlugginInterface) theRunTimeClass.getConstructor().newInstance();

                thePluggin.start(theMainAPI);

//                for (Method method : theRunTimeClass.getMethods()) {
//                    Annotation annotation = method.getAnnotation(CreateRepeatEvents.class);
//                    if (annotation != null) {
//                        method.invoke(thePluggin, "THis is the main event", LocalDate.now(), LocalTime.now(), 2);
//                    }
//                }


            }
        } catch (Exception e) {
//            TODO:The exeption need to be defined not generic
            throw new RuntimeException(e);
        }


    }
}
