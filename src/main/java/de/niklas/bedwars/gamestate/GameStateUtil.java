package de.niklas.bedwars.gamestate;

import de.niklas.bedwars.Bedwars;

public class GameStateUtil {

    private Bedwars plugin;
    private GameState currentGameState;
    private GameState[] gameStates;

    public GameStateUtil(Bedwars plugin){
        this.plugin = plugin;
        gameStates = new GameState[3];
        gameStates[GameState.WAITING_STATE] = new WaitingState();
        gameStates[GameState.INGAME_STATE] = new IngameState();
        gameStates[GameState.END_STATE] = new EndState();
    }

    public void setGameState(int gameStateID){
        if(currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopGameState(){
        if(currentGameState != null){
            currentGameState.stop();
            currentGameState = null;
        }
    }
}
