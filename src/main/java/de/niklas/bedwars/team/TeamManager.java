package de.niklas.bedwars.team;

import de.niklas.bedwars.Bedwars;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private HashMap<Player, Teams> teamHashMap;

    public TeamManager(){
        teamHashMap = new HashMap<>();
    }

    public Teams getPlayerTeam(Player player){
        return teamHashMap.containsKey(player) ? teamHashMap.get(player) : null;
    }

    public void setPlayerTeam(Player player, Teams team) {
        if(getPlayerTeam(player) != null)
            getPlayerTeam(player).getTeamPlayers().remove(player);
        team.getTeamPlayers().add(player);
        teamHashMap.put(player, team);
    }

    public void removePlayerTeam(Player player) {
        Teams team = getPlayerTeam(player);
        team.getTeamPlayers().remove(player);
        teamHashMap.remove(player);
    }

    private Teams getLeastPlayersTeam() {
        Teams currentLeastPlayerTeam = Teams.values()[0];
        for(Teams current : Teams.values()) {
            if(current.getTeamPlayers().size() < currentLeastPlayerTeam.getTeamPlayers().size())
                currentLeastPlayerTeam = current;
        }
        return currentLeastPlayerTeam;
    }

    private Teams getMostPlayersTeam() {
        Teams currentMostPlayersTeam = Teams.values()[0];
        for(Teams current : Teams.values()) {
            if(current.getTeamPlayers().size() > currentMostPlayersTeam.getTeamPlayers().size())
                currentMostPlayersTeam = current;
        }
        return currentMostPlayersTeam;
    }
}
