package de.niklas.bedwars.team;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam {

    private Teams teams;
    private final Location spawnLocation;
    private final Location bedLocation;
    private List<UUID> players;
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
}
