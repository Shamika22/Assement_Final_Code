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
import calenderapp.calplugins.*;

import java.lang.reflect.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.python.core.*;
//import org.python.util.*;


public class App {
    public static void main(String[] args) {
//      VARIABLES AND CONSTS
        Scanner userIn = new Scanner(System.in);  // Create a Scanner object
        boolean quiteMain = false;
        List<Event> theEventList = new ArrayList<>();

        PlugginLoader thePlugginLoader = new PlugginLoader();
        thePlugginLoader.loadPluggins();

//        try{
//            InputStream inputStream = MyParser.class.getResourceAsStream("/" + "input.txt");
//            MyParser theParser = new MyParser(inputStream);
//            ParseObject theParsedObjectList = theParser.parse("input.txt");
//
//            System.out.println();
//        }catch (Exception e){
//            System.out.println(e);
//        }





//        Loading pluggins
        TempHardCodedCalendar temFile = new TempHardCodedCalendar();
        String[] pluginNames = temFile.getPluginNames();

//        Intialize the Calender API
        CalenderAppAPI theMainAPI = new CalenderAppAPI() {

            @Override
            public void printVal(String theVal) {

            }

            @Override
            public void addEvent(List<Event> theEventLisIn) {
                theEventList.addAll(theEventLisIn);
            }
        };


        try {
            Class<?> theRunTimeClass = Class.forName("calenderapp.calplugins.Repeat");
            CalenderPlugginInterface thePluggin = (CalenderPlugginInterface) theRunTimeClass.getConstructor().newInstance();

            thePluggin.start(theMainAPI);

            for (Method method : theRunTimeClass.getMethods()) {
                Annotation annotation = method.getAnnotation(CreateRepeatEvents.class);
                if (annotation != null) {
                    method.invoke(thePluggin, "THis is the main event", LocalDate.now(), LocalTime.now(), 2);
                }
            }




        } catch (Exception e) {
//            TODO:The exeption need to be defined not generic
            throw new RuntimeException(e);
        }


        do {
            printMainMenue();

            try {
                int mainMenueSelection = Integer.parseInt(userIn.nextLine());

                switch (mainMenueSelection) {
                    case 1: {
//                        handleCalenderMenue(userIn , theEventList);
                        break;
                    }
                    case 2: {

//                        PythonInterpreter interpreter = new PythonInterpreter();
//                        String pythonScript = "result = 5 + 3\n" +
//                                "print('The sum of 5 and 3 is:', result)";
//                        interpreter.exec(pythonScript);


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


    private static void handleCalenderMenue(Scanner userIn , List<Event> theEventList) {
        boolean quiteSubMenue = false;
        Scanner theCalenderInput = userIn;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime selectedTime = now;

//    starting print call
        printCalender(LocalDateTime.now() , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
        printCalenderMenue();


        while (!quiteSubMenue) {

            try {
//                TODO:Here the calender render need to be callled
                String calenderMenueInput = theCalenderInput.nextLine();

                switch (calenderMenueInput) {
                    case "q": {
                        quiteSubMenue = true;
                        break;
                    }
                    case "+d": {
                        selectedTime = selectedTime.plusDays(1);


                        printCalender(selectedTime ,filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "+w": {
                        selectedTime = selectedTime.plusWeeks(1);

                        printCalender(selectedTime ,filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "+m": {
                        selectedTime = selectedTime.plusMonths(1);

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "+y": {
                        selectedTime = selectedTime.plusYears(1);

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "-d": {
                        selectedTime = selectedTime.minusDays(1);

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "-w": {
                        selectedTime = selectedTime.minusWeeks(1);

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "-m": {
                        selectedTime = selectedTime.minusMonths(1);

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "-y": {
                        selectedTime = selectedTime.minusYears(1);

                        printCalender(selectedTime ,filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                    case "t": {
                        selectedTime = LocalDateTime.now();

                        printCalender(selectedTime , filterWeeklyEvents(selectedTime.toLocalDate() , theEventList ));
                        printCalenderMenue();
                        break;
                    }
                }
                ;


            } catch (InputMismatchException error) {
                System.out.println(error);
                theCalenderInput.nextInt();//clear the buffer by consuming that
            }

        }
        ;


    }

    private static void printCalenderMenue() {

        System.out.println("Time Travel Menu\n" +
                "\n" +
                "Forward Time Travel:" + "       " + "Backward Time Travel:" + "       " + "Others:" +
                "\n" +
                "+d: Go forward 1 day" + "        " + "-d: Go back 1 day" + "         " + "t:Return to Today\n" +
                "+w: Go forward 1 week" + "       " + "-w: Go back 1 week" + "        " + "q:Back to main menue\n" +
                "+m: Go forward 1 month" + "      " + "-m: Go back 1 month\n" +
                "+y: Go forward 1 year" + "       " + "-y: Go back 1 year\n"
        );

        System.out.println("Please input a control");

    }

    private static void printMainMenue() {
        System.out.println("Welcome to the calender application");
        System.out.println("Main Menue");
        System.out.println("1. Go to calender");
        System.out.println("2. Select locale");
        System.out.println("3. Exit");

    }

    private static void getLocale() {
        System.out.println("Get Locale");

    }

    private static void printCalender(LocalDateTime refTime , List<EventPrintObject> theEventList) {

        String[] dateList = intializeDates(refTime);
        String[][] messages = new String[25][7];
        String[] rowHeadings = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};

        //Initlaize the calender to null
        for(int i=0;i<25;i++){
            for(int j=0;j<7;j++){
                messages[i][j] = " ";
            }
        }

        //add events based on there date and time
        for(EventPrintObject theEventPrintObj : theEventList){
            int theXVal = theEventPrintObj.getxVal();
            int theYVal = theEventPrintObj.getyVal();
            messages[theXVal][theYVal] = theEventPrintObj.getTheEvent().getTitle();
        }


        // Initialising
        var terminalGrid = TerminalGrid.create();

        // With limited space
        terminalGrid.setTerminalWidth(130);
        terminalGrid.print(messages, rowHeadings, dateList);
        System.out.println();
        terminalGrid.setTerminalWidth(120);

    }

    private static String[] intializeDates(LocalDateTime refDate) {
        String[] dateList = new String[7];
        LocalDateTime newDate = refDate;
        dateList[0] = refDate.getDayOfWeek().toString() + "\n" + refDate.toLocalDate().toString();

        for (int i = 1; i < 7; i++) {
            newDate = newDate.plusDays(1);
            dateList[i] = newDate.getDayOfWeek().toString() + "\n" + newDate.toLocalDate().toString();
        }

        return dateList;
    }

    private static List<EventPrintObject> filterWeeklyEvents(LocalDate refDate , List<Event> eventList){
        List<EventPrintObject> weekelyEvents = new ArrayList<>();
        LocalDate currentDate = refDate;

//        filtering all the weekly events
        for (int i = 0; i < 7; i++) {
            for(Event theEvent : eventList){
                if(theEvent.getStartDate().isEqual(currentDate)){
                    System.out.println("has a match");
                    System.out.println();
                    EventPrintObject theEventPrintObject = new EventPrintObject(theEvent,theEvent.getStartTime().getHour(),i);
                    weekelyEvents.add(theEventPrintObject);
                }
            }
            currentDate = currentDate.plusDays(1);
        }

        return  weekelyEvents;
    }


}
