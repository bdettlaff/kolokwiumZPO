package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEvent {

    public List<Event> listOfEvents;
    public int pass=0;
    public boolean passFlag = false;
    public boolean shotFlag = false;
    public int cycle = 0;
    public int initiative = 1;

    public void setListOfPauseEvents(List<String> listOfPauseEvents) {
        this.listOfPauseEvents = listOfPauseEvents;
    }

    public List<String> listOfPauseEvents;
    public String previousEvent;

    RandomEvent() throws IOException {
        JsonDecoder jsonDecoder = new JsonDecoder();
        this.listOfEvents = jsonDecoder.getJsonList();
    }

    public Event selectEvent(){
        //System.out.println(cycle);
        int eventNumber=0;
        if(pass<3 && !passFlag){
            eventNumber = randomEvent(4);
            pass++;
            cycle++;
            //System.out.println("Podanie wejscie");
        }else if(pass==3){
            pass=0;
            passFlag = true;
        }

        if(randomShotOrPickup()==0 && passFlag && cycle == 4){
            //shot
            eventNumber = randomEvent(0);
            shotFlag = true;
            cycle++;
            //System.out.println("Strzał");
        }else if(randomShotOrPickup()==12 && passFlag && cycle == 4){
            eventNumber = randomEvent(12);
            shotFlag = false;
            passFlag = false;
            cycle = 0;
            switchInitiative();
            //System.out.println("Pickup");
        }

        if(randomGolOrMiss()==8 && passFlag && shotFlag && cycle == 6){
            //gol - zmiana inicjatywy
            eventNumber = randomEvent(8);
            passFlag = false;
            shotFlag = false;
            cycle = 0;
            switchInitiative();
            //System.out.println("Gol");
        }else if(randomGolOrMiss()==16 && passFlag && shotFlag && cycle == 6){
            //pudlo - zmiana inicjatywy
            eventNumber = randomEvent(16);
            passFlag = false;
            shotFlag = false;
            cycle = 0;
            switchInitiative();
            //System.out.println("Miss");
        }

        if(cycle==3){
            cycle++;
        }

        if(cycle==5){
            cycle++;
        }
        previousEvent = listOfEvents.get(eventNumber).getEventType();
        return listOfEvents.get(eventNumber);
    }

    public int randomEvent(int jump){
        int eventNumber=0;
        Random generator = new Random();
        eventNumber = generator.nextInt(4)+jump;

        return eventNumber;
    }

    public int randomShotOrPickup(){
        int jump=0;
        Random generator = new Random();
        int eventNumber=0;
        eventNumber = generator.nextInt(10);
        //System.out.println("SOP:" + eventNumber);
        if(eventNumber < 4){
            return jump;
        }

        jump = 12;
        return jump;
    }

    public int randomGolOrMiss(){
        int jump=0;
        Random generator = new Random();
        int eventNumber=0;
        eventNumber = generator.nextInt(10);
        //System.out.println("GOM:" + eventNumber);
        if(eventNumber < 10){
            jump =8;
            return jump;
        }

        jump = 16;
        return jump;
    }

    public String selectEventForPause(){
        prepareListOfPauseEvents();

        Random generator = new Random();
        int eventNumber = generator.nextInt(4);

        return listOfPauseEvents.get(eventNumber);
    }

    public void prepareListOfPauseEvents(){
        List<String> temp = new ArrayList<>();
        temp.add("<Krzyki z trybun> Auuuuuu");
        temp.add("<Krzyki z trybun> Do boju!");
        temp.add("<Krzyki z trybun> Nic się nie stało!");
        temp.add("<Krzyki z trybun> Sędzia kalosz!");

        setListOfPauseEvents(temp);
    }

    public void switchInitiative(){
        initiative = initiative * -1;
    }
}
