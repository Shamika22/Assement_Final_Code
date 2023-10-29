package calenderapp.app;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.CreateRepeatEvents;
import calenderapp.utilities.Event;
import calenderapp.utilities.ParseObject;
import calenderapp.utilities.PlugginParser;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
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



    public boolean getEvents(String fileName) {
        Boolean readingState = true;
        try {

            File file = new File("src/main/resources/" + fileName);

            if(file.exists()){
                FileReader reader = new FileReader(file, StandardCharsets.UTF_8);

                if (fileName.contains("utf8")) {
                    reader = new FileReader(file, StandardCharsets.UTF_8);
                } else if (fileName.contains("utf16")) {
                    reader = new FileReader(file, StandardCharsets.UTF_16);
                } else {
//                32 is not supported in my version
                }


                MyParser theParser = new MyParser(reader);

                ParseObject theParsedObjectList = theParser.parse("input.txt");
                theMainEventlist = theParsedObjectList.getEventList();
                thePlugginObjList = theParsedObjectList.getPlugginList();
            }else{
                readingState = false;
            }


        } catch (Exception e) {
            System.out.println(e);
        }


        return readingState;

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



                Class<?> theRunTimeClass = Class.forName(thePluginParser.getPlugginID());
                CalenderPlugginInterface thePluggin = (CalenderPlugginInterface) theRunTimeClass.getConstructor().newInstance();

                thePluggin.start(theMainAPI , thePluginParser.getArgumentMap());

                String uniqueIdentifier = Integer.toHexString(System.identityHashCode(thePluggin));


                theActivePluginList.put(uniqueIdentifier,thePluggin);


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
