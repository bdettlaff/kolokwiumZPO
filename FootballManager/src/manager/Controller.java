package manager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.TimerTask;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Controller {
    static int timer=0;
    //public Logger logger = Logger.getLogger("ManagerLogs");
    public FileHandler fileHandler;

    public Team firstTeam;
    public Team secondTeam;
    public int firstTeamScoreCounter=0;
    public int secondTeamScoreCounter=0;

    @FXML
    Label mainTimer;
    @FXML
    Button startRaceButton;
    @FXML
    Label labelContentOne;
    @FXML
    Label labelContentTwo;
    @FXML
    Label labelContentThree;
    @FXML
    Label labelContentFour;
    @FXML
    Label labelContentFive;
    @FXML
    Label teamNameOne;
    @FXML
    Label teamNameTwo;
    @FXML
    Label teamNameOneStat;
    @FXML
    Label teamNameTwoStat;
    @FXML
    ImageView firstBall;
    @FXML
    ImageView secondBall;
    @FXML
    Label firstTeamScore;
    @FXML
    Label secondTeamScore;


    RandomEvent randomEvent = new RandomEvent();
    Boolean isPause = false;
    Integer timerObject;
    Timer timerNew = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                beginMatch();
                editContent();
            });

            timer++;

            if(timer==105){
                //logger.info("Match end");
                timerNew.cancel();
                timerNew.purge();
            }
        }
    };

    public Controller() throws IOException {
        TeamCreator teamCreator = new TeamCreator();
        teamCreator.getPlayersFromFile();
        teamCreator.createTeam("MU");
        firstTeam = new Team(teamCreator.clubList,"MU");
        teamCreator.createTeam("CHE");
        secondTeam = new Team(teamCreator.clubList,"CHE");
    }

    public void startMatch() throws IOException {
        startRaceButton.setDisable(true);

        try {
            fileHandler = new FileHandler("/Users/dettlaffb/Desktop/FootballManager/src/logs.txt");
            //logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            //logger.info("Match begin");

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        start();
    }

    private void beginMatch() {
        teamNameOne.setText("MU");
        teamNameTwo.setText("CHE");
        teamNameOneStat.setText("MU");
        teamNameTwoStat.setText("CHE");

        timerObject = timer;
        if(timerObject < 10){
            mainTimer.setText("0"+timerObject.toString()+":00");
            isPause = false;
        }else if(timerObject>45 && timerObject<60){
            mainTimer.setText("PAUSE");
            isPause = true;
        }else if(timerObject <= 45 && timerObject >= 10){
            mainTimer.setText(timerObject.toString()+":00");
            isPause = false;
        }else {
            timerObject = timer - 15;
            mainTimer.setText(timerObject.toString()+":00");
            isPause = false;
        }

    }

    private void editContent(){

        Event event = new Event();

        if(timerObject>45 && timerObject<60 && isPause){
            labelContentFive.setText(labelContentFour.getText());
            labelContentFour.setText(labelContentThree.getText());
            labelContentThree.setText(labelContentTwo.getText());
            labelContentTwo.setText(labelContentOne.getText());
            labelContentOne.setText(randomEvent.selectEventForPause());
        }
        else{
            labelContentFive.setText(labelContentFour.getText());
            labelContentFour.setText(labelContentThree.getText());
            labelContentThree.setText(labelContentTwo.getText());
            labelContentTwo.setText(labelContentOne.getText());
            event = randomEvent.selectEvent();
            labelContentOne.setText(event.getContent());
            checkGoal(event);
            //logger.info("TYPE: " +  randomEvent.selectEvent().getEventType() + " CONTENT: " + randomEvent.selectEvent().getContent());
        }



        if(randomEvent.initiative == 1){
            firstBall.setVisible(true);
            secondBall.setVisible(false);
        }else{
            firstBall.setVisible(false);
            secondBall.setVisible(true);
        }
    }

    public void start(){
        timerNew.scheduleAtFixedRate(task,0,500);
    }


    public void checkGoal(Event event){
        if(event.getEventType().equals("gol") && randomEvent.initiative == -1){
            firstTeamScoreCounter++;
            firstTeamScore.setText(String.valueOf(firstTeamScoreCounter));
        }else if(event.getEventType().equals("gol") && randomEvent.initiative == 1){
            secondTeamScoreCounter++;
            secondTeamScore.setText(String.valueOf(secondTeamScoreCounter));
        }

    }
}

