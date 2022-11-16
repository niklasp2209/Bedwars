package de.niklas.bedwars.game;

import de.niklas.bedwars.game.team.GameTeam;

import java.util.UUID;

public class GamePlayer {

    private GameTeam gameTeam;
    private UUID uuid;

    private GamePlayer(GameTeam gameTeam, UUID uuid){
        this.gameTeam = gameTeam;
        this.uuid = uuid;
    }

    public GameTeam getGameTeam() {
        return gameTeam;
    }

    public void setGameTeam(GameTeam gameTeam) {
        this.gameTeam = gameTeam;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
