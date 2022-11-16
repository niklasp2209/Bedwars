package de.niklas.bedwars.team;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam {

    private Teams teams;
    private final Location spawnLocation;
    private final Location bedLocation;
    private List<Player> players;
    private boolean bedAlive;

    private GameTeam(Teams teams, Location spawnLocation, Location bedLocation){
        this.teams = teams;
        this.spawnLocation = spawnLocation;
        this.bedLocation = bedLocation;
        this.players = new ArrayList<>();
        this.bedAlive = true;
    }

    public static GameTeam create(Teams teams, Location spawnLocation, Location bedLocation){
        return new GameTeam(teams, spawnLocation, bedLocation);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
