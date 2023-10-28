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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlugginLoader {
    private List<Event> theMainEventlist = new ArrayList<>();
    private List<PlugginParser> thePlugginObjList;

    private Map<String , CalenderPlugginInterface> theActivePluginList = new HashMap<>();



    public void getEvents() {

        try {
            InputStream inputStream = MyParser.class.getResourceAsStream("/" + "input.txt");
            MyParser theParser = new MyParser(inputStream);
            ParseObject theParsedObjectList = theParser.parse("input.txt");
            theMainEventlist = theParsedObjectList.getEventList();
            thePlugginObjList = theParsedObjectList.getPlugginList();

        } catch (Exception e) {
            System.out.println(e);
        }

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
            for (PlugginParser thePluginParser : thePlugginObjList) {

                System.out.println(thePluginParser.getPlugginID());

                Class<?> theRunTimeClass = Class.forName(thePluginParser.getPlugginID());
                CalenderPlugginInterface thePluggin = (CalenderPlugginInterface) theRunTimeClass.getConstructor().newInstance();

                thePluggin.start(theMainAPI , thePluginParser.getArgumentMap());

                theActivePluginList.put(thePluginParser.getPlugginID(),thePluggin);


            }
        } catch (Exception e) {
//            TODO:The exeption need to be defined not generic
            throw new RuntimeException(e);
        }

    }

    public List<Event> getTheMainEventlist() {
        return theMainEventlist;
    }

    public List<PlugginParser> getThePlugginObjList() {
        return thePlugginObjList;
    }

    public Map<String, CalenderPlugginInterface> getTheActivePluginList() {
        return theActivePluginList;
    }
}
