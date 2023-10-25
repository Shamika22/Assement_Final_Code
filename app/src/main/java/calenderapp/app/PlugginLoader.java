package calenderapp.app;

import calenderapp.utilities.ParseObject;

import java.io.InputStream;

public class PlugginLoader {
    public void loadPluggins(){
        try{
            InputStream inputStream = MyParser.class.getResourceAsStream("/" + "input.txt");
            MyParser theParser = new MyParser(inputStream);
            ParseObject theParsedObjectList = theParser.parse("input.txt");

            System.out.println(theParsedObjectList.getEventList());
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
