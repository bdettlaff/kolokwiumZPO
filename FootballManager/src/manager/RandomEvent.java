package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEvent {

    public List<Event> listOfEvents;

    public void setListOfPauseEvents(List<String> listOfPauseEvents) {
        this.listOfPauseEvents = listOfPauseEvents;
    }

    public List<String> listOfPauseEvents;
    public String previousEvent;

    RandomEvent() throws IOException {
        JsonDecoder jsonDecoder = new JsonDecoder();
        this.listOfEvents = jsonDecoder.getJsonList();
    }

    public String selectEvent(){

        Random generator = new Random();
        int eventNumber = generator.nextInt(20);

        previousEvent = listOfEvents.get(eventNumber).getEventType();
        return listOfEvents.get(eventNumber).getContent();
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
}
