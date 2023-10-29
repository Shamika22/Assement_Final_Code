package calenderapp.app;

import calenderapp.api.CalenderAppAPI;
import calenderapp.api.CalenderPlugginInterface;
import calenderapp.utilities.Event;
import calenderapp.utilities.ParseObject;
import calenderapp.utilities.PlugginParser;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlugginLoader {
    private List<Event> theMainEventlist = new ArrayList<>();
    private List<PlugginParser> thePlugginObjList;

    private Map<String , CalenderPlugginInterface> theActivePluginList = new HashMap<>();


    @SuppressWarnings("PMD.CloseResource")  // I am getting warning here due to the file readers and I have clsoed the file readers safery as soon as the file is fully finished reading
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
                }


                MyParser theParser = new MyParser(reader);

                ParseObject theParsedObjectList = theParser.parse("input.txt");
                theMainEventlist = theParsedObjectList.getEventList();
                thePlugginObjList = theParsedObjectList.getPlugginList();

                reader.close();

            }else{
                readingState = false;
            }


        } catch (IOException | ParseException e){
            System.out.println("Encorder error" +e);
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


        }
        catch (NoSuchMethodException | ClassNotFoundException e){
            System.out.println("Reflection error"+e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Runtime error"+e);
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
