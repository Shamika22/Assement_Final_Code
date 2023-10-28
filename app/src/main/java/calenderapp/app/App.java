/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package calenderapp.app;

import calenderapp.utilities.*;


import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalDateTime;

import calenderapp.api.*;

import java.lang.reflect.*;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.TextStyle;
import java.util.*;
//import org.python.core.*;
//import org.python.util.*;


public class App {
    public static void main(String[] args) {
//      VARIABLES AND CONSTS
        int column = 7; // Default colum size
        int row = 26; // Default row size
        boolean quiteMain = false;
        Scanner userIn = new Scanner(System.in);  // Create a Scanner object
        List<Event> theEventList = new ArrayList<>(); // This is the main event list
        Map<String,CalenderPlugginInterface> theActivePlugginList;

//      Locale Settings
        Locale systemDefaultLocale = Locale.forLanguageTag(Locale.getDefault().toLanguageTag());
//      Loading the bundle
        ResourceBundle systemDefaultBundle = ResourceBundle.getBundle("bundle", systemDefaultLocale);

        System.out.println(systemDefaultBundle.getString("SubMenueOptionTwo"));

//      READ THE PARSER AND LOAD THE INITIAL EVENT DATA
        PlugginLoader thePlugginLoader = new PlugginLoader();
        thePlugginLoader.getEvents();
        thePlugginLoader.loadPluggins();

//      TAKE ALL ACTIVE PLUGINS AND CRATED EVENTS
        theEventList = thePlugginLoader.getTheMainEventlist();
        theActivePlugginList = thePlugginLoader.getTheActivePluginList();


//      START THE MENUES
        do {
            printMainMenue(systemDefaultBundle);
            try {
                int mainMenueSelection = Integer.parseInt(userIn.nextLine());
                switch (mainMenueSelection) {
                    case 1: {

                      handleCalenderMenue(userIn , theEventList , column , row , systemDefaultBundle , systemDefaultLocale);
                        break;
                    }
                    case 2: {
                        System.out.println("Please enter a valid locale type");
                        systemDefaultLocale = Locale.forLanguageTag(userIn.nextLine());
                        systemDefaultBundle = ResourceBundle.getBundle("bundle", systemDefaultLocale);
                        break;
                    }
                    case 3: {
                        quiteMain = true;
                        break;
                    }

                    default: {
                        break;
                    }

                }

            } catch (InputMismatchException error) {
                System.out.println(error);
                userIn.nextLine();
            }


        } while (!quiteMain);


    }


    private static void handleCalenderMenue(Scanner userIn , List<Event> theEventList , int column , int row , ResourceBundle theSetBundle , Locale theSetLocale) {
        boolean quiteSubMenue = false;
        LocalDateTime selectedTime = LocalDateTime.now();

//    starting print call
        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale);
        printCalenderMenue(theSetBundle);

        while (!quiteSubMenue) {

            try {
//                TODO:Here the calender render need to be callled
                String calenderMenueInput = userIn.nextLine();

                switch (calenderMenueInput) {
                    case "q": {
                        quiteSubMenue = true;
                        break;
                    }
                    case "+d": {
                        selectedTime = selectedTime.plusDays(1);


                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row ,theSetLocale);
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "+w": {
                        selectedTime = selectedTime.plusWeeks(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale );
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "+m": {
                        selectedTime = selectedTime.plusMonths(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale );
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "+y": {
                        selectedTime = selectedTime.plusYears(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale);
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "-d": {
                        selectedTime = selectedTime.minusDays(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale);
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "-w": {
                        selectedTime = selectedTime.minusWeeks(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale);
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "-m": {
                        selectedTime = selectedTime.minusMonths(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row , theSetLocale );
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "-y": {
                        selectedTime = selectedTime.minusYears(1);

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row ,theSetLocale );
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "t": {
                        selectedTime = LocalDateTime.now();

                        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList , row) , column , row, theSetLocale );
                        printCalenderMenue(theSetBundle);
                        break;
                    }
                    case "s":{
                        selectedTime = LocalDateTime.now();
                        System.out.println("Please enter an event to search ");
                        String theSearchEvent = userIn.nextLine();

//                        Do the seach for the array
                        Event theEvent = searchEvent(theEventList,theSearchEvent , selectedTime);
                        if(theEvent == null){
//                            TODO: Add this to the bundle
                            System.out.println("No such event");
                        }else{
                            System.out.println("********** SELECTED EVENT DETAILS ARE ************");
                            System.out.println(theEvent.getTitle());
                            System.out.println(theEvent.getStartDate());
                            System.out.println(theEvent.getStartTime());
                            System.out.println(theEvent.getDuration());
                            System.out.println(theEvent.getRepeat());

                        }

                        break;
                    }
                    default: {

                        break;
                    }
                }
                ;


            } catch (InputMismatchException error) {
                System.out.println(error);
                userIn.nextInt();//clear the buffer by consuming that
            }

        }
        ;


    }

    private static Event searchEvent(List<Event> theEventList , String seachTerm , LocalDateTime theRefDate){
        Event theSelectedEvent = null;
        LocalDateTime theEndDate = theRefDate.plusYears(1);

        for(Event theEvent : theEventList){
            if((theEvent.getStartDate().isEqual(ChronoLocalDate.from(theRefDate)) || theEvent.getStartDate().isBefore(ChronoLocalDate.from(theEndDate))||theEvent.getStartDate().isAfter(ChronoLocalDate.from(theRefDate)) ) && (theEvent.getTitle().equals(seachTerm))){
                System.out.println("A match found");
                theSelectedEvent = theEvent;
                break;
            }
        }
        return theSelectedEvent;
    }

    private static void printCalenderMenue(ResourceBundle theSetBundle) {

        System.out.println(theSetBundle.getString("SubTitle")+"\n" +
                "\n" +
                "+d: "+theSetBundle.getString("SubMenueOptionOne") + "        " + "-d: "+theSetBundle.getString("SubMenueOptionTwo") + "         " + "t: "+theSetBundle.getString("SubMenueOptionThree")+"\n" +
                "+w: "+theSetBundle.getString("SubMenueOptionFour") + "       " + "-w: "+theSetBundle.getString("SubMenueOptionFive") + "        " + "q: "+theSetBundle.getString("SubMenueOptionSix")+"\n" +
                "+m: "+theSetBundle.getString("SubMenueOptionSeven") + "      " + "-m: "+theSetBundle.getString("SubMenueOptionEight") + "        " + "s: "+theSetBundle.getString("SubMenueOptionSix")+"\n" +
                "+y: "+theSetBundle.getString("SubMenueOptionNine") + "       " + "-y: "+theSetBundle.getString("SubMenueOptionTen") + "\n"
        );


        System.out.println("Please input a control");

    }

    private static void printMainMenue(ResourceBundle theSetBundle) {
        System.out.println(theSetBundle.getString("MainTitle"));
        System.out.println(theSetBundle.getString("Heading"));
        System.out.println("1."+theSetBundle.getString("MainMenueOptionOne"));
        System.out.println("2."+theSetBundle.getString("MainMenueOptionTwo"));
        System.out.println("3."+theSetBundle.getString("MainMenueOptionThree"));
    }

    private static void printCalender(LocalDateTime refTime , List<EventPrintObject> theEventList , int column , int row , Locale theSetLocale) {


        String[] dateList = intializeDates(refTime , column , theSetLocale);
        String[][] messages = new String[row][column];
        String[] rowHeadings = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" , "Full-Day-Events"};

        //Initlaize the calender to null
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                messages[i][j] = " ";
            }
        }

        //add events based on there date and time
        for(EventPrintObject theEventPrintObj : theEventList){
            int theXVal = theEventPrintObj.getxVal();
            int theYVal = theEventPrintObj.getyVal();
            messages[theXVal][theYVal] = messages[theXVal][theYVal] +"|"+ theEventPrintObj.getTheEvent().getTitle();
        }


        // Initialising
        var terminalGrid = TerminalGrid.create();

        // With limited space
        terminalGrid.setTerminalWidth(130);
        terminalGrid.print(messages, rowHeadings, dateList);
        System.out.println();
        terminalGrid.setTerminalWidth(120);

    }

    private static String[] intializeDates(LocalDateTime refDate , int col , Locale theSetLocale) {
        String[] dateList = new String[col];
        LocalDateTime newDate = refDate;
        dateList[0] = refDate.getDayOfWeek().getDisplayName(TextStyle.FULL,theSetLocale) + "\n" + refDate.toLocalDate().toString();
        for (int i = 1; i < col; i++) {
            newDate = newDate.plusDays(1);
            dateList[i] = newDate.getDayOfWeek().getDisplayName(TextStyle.FULL,theSetLocale) + "\n" + newDate.toLocalDate().toString();
        }
        return dateList;
    }

    private static List<EventPrintObject> filterWeeklyEvents(LocalDate refDate , List<Event> eventList , int row){
        List<EventPrintObject> weekelyEvents = new ArrayList<>();
        LocalDate currentDate = refDate;
//        filtering all the weekly events
        for (int i = 0; i < 7; i++) {
            for(Event theEvent : eventList){
                if(theEvent.getStartDate().isEqual(currentDate)){

                    if(theEvent.isAllDay()){
                        EventPrintObject theEventPrintObject = new EventPrintObject(theEvent,row-1,i);
                        weekelyEvents.add(theEventPrintObject);
                    }else{
                        EventPrintObject theEventPrintObject = new EventPrintObject(theEvent,theEvent.getStartTime().getHour(),i);
                        weekelyEvents.add(theEventPrintObject);
                    }

                }
            }
            currentDate = currentDate.plusDays(1);
        }

        System.out.println(weekelyEvents);

        return  weekelyEvents;
    }


}
