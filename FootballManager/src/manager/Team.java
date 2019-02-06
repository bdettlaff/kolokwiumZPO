package manager;

import java.util.List;

public class Team {
    public String name;
    public List<Player> listOfPlayers;

    public Team(List<Player> listOfPlayers, String name){
        this.listOfPlayers = listOfPlayers;
        this.name = name;
    }
}
