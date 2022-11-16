package de.niklas.bedwars.game.team;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam {

    private final GameTeamType gameTeamType;
    private Location spawnLocation;
    private Location bedLocation;
    private List<Player> players;
    private boolean bedGone;

    private GameTeam(GameTeamType gameTeamType, Location spawnLocation, Location bedLocation){
        this.gameTeamType = gameTeamType;
        this.spawnLocation = spawnLocation;
        this.bedLocation = bedLocation;
        this.players = new ArrayList<>();
        this.bedGone = false;
    }

    public static GameTeam create(GameTeamType gameTeamType, Location spawnLocation, Location bedLocation){
        return new GameTeam(gameTeamType, spawnLocation, bedLocation);
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public void setBedLocation(Location bedLocation) {
        this.bedLocation = bedLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public GameTeamType getGameTeamType() {
        return gameTeamType;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setBedGone(boolean bedGone) {
        this.bedGone = bedGone;
    }
}
