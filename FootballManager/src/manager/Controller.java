package manager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.TimerTask;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Controller {
    static int timer=0;
    public Logger logger = Logger.getLogger("CyclistLogs");
    public FileHandler fileHandler;

    @FXML
    Label mainTimer;
    @FXML
    Button startRaceButton;

    Integer timerObject;
    Timer timerNew = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                beginMatch();
            });

            timer++;

            if(timer==105){
                logger.info("Match end");
                timerNew.cancel();
                timerNew.purge();
            }
        }
    };

    public void startMatch() throws IOException {
        startRaceButton.setDisable(true);

        try {
            fileHandler = new FileHandler("/Users/dettlaffb/Desktop/FootballManager/src/logs.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.info("Match begin");

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        start();
    }

    private void beginMatch() {
        timerObject = timer;
        if(timerObject < 10){
            mainTimer.setText("0"+timerObject.toString()+":00");
        }else if(timerObject>45 && timerObject<60){
            mainTimer.setText("PRZERWA");
        }else if(timerObject <= 45 && timerObject >= 10){
            mainTimer.setText(timerObject.toString()+":00");
        }else {
            timerObject=timerObject-15;
            mainTimer.setText(timerObject.toString()+":00");
        }
    }

    public void start(){
        timerNew.scheduleAtFixedRate(task,0,500);
    }

}

