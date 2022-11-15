package de.niklas.bedwars.gamestate;

public abstract class GameState {

    public static final int WAITING_STATE = 0,
                            INGAME_STATE = 1,
                            END_STATE = 2;

    public abstract void start();
    public abstract void stop();
}
