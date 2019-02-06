package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamCreator {

    public List<Player> listOfPlayers;
    public List<Player> clubList;

    public void getPlayersFromFile() throws IOException {
        List<Player> temporaryList = new ArrayList<>();

        BufferedReader in = new BufferedReader(new FileReader("/Users/dettlaffb/Desktop/FootballManager/src/ListOfPlayers.txt"));
        String line;

        String club;

        while((line = in.readLine()) != null)
        {
            club = in.readLine();
            Player player = new Player(line,club);
            temporaryList.add(player);
        }

        listOfPlayers = temporaryList;

        in.close();
    }

    public void createTeam(String club){

        List<Player> temporaryList = listOfPlayers.stream()
                .filter(b -> b.club.equals(club))
                .collect(Collectors.toList());

            clubList=temporaryList;
    }
}
