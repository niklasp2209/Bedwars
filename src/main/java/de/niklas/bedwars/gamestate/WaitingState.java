package de.niklas.bedwars.gamestate;

import de.niklas.bedwars.countdowns.LobbyCountdown;

public class WaitingState extends GameState {

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYERS = 8;

    private LobbyCountdown lobbyCountdown;

    public WaitingState(GameStateUtil gameStateUtil){
        lobbyCountdown = new LobbyCountdown(gameStateUtil);
    }

    @Override
    public void start() {
        lobbyCountdown.startIdle();
    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
