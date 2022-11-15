package de.niklas.bedwars.team;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private HashMap<Player, Team> teamConnector;

    public TeamManager(){
        teamConnector = new HashMap<>();
    }

    public Team getPlayerTeam(Player player){
        return teamConnector.containsKey(player) ? teamConnector.get(player) : null;
    }

    public void setPlayerTeam(Player player, Team team){
        if(getPlayerTeam(player) != null)
            getPlayerTeam(player).getTeamPlayers().remove(player);
        team.getTeamPlayers().add(player);
        teamConnector.put(player, team);
    }

    public void removePlayerTeam(Player player){
        Team team = getPlayerTeam(player);
        team.getTeamPlayers().remove(player);
        teamConnector.remove(player);
    }

    private Team getLeastPlayersTeam(){
        Team currentLeastTeam = Team.values()[0];
        for(Team current : Team.values()){
            if(current.getTeamPlayers().size() < currentLeastTeam.getTeamPlayers().size())
                currentLeastTeam = current;
        }
        return currentLeastTeam;
    }

    private Team getMostPlayersTeam(){
        Team currentMostTeam = Team.values()[0];
        for(Team current : Team.values()){
            if(current.getTeamPlayers().size() > currentMostTeam.getTeamPlayers().size())
                currentMostTeam = current;
        }
        return currentMostTeam;
    }

    public boolean balancedTeams(){
        int difference = getMostPlayersTeam().getTeamPlayers().size() - getLeastPlayersTeam().getTeamPlayers().size();
        if(difference >= 2)
            return false;
        return true;
    }
}
