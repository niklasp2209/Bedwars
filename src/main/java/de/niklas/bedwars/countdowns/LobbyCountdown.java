package de.niklas.bedwars.countdowns;

import de.niklas.bedwars.countdowns.Countdown;
import de.niklas.bedwars.gamestate.GameState;
import de.niklas.bedwars.gamestate.GameStateUtil;
import de.niklas.bedwars.gamestate.WaitingState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private GameStateUtil gameStateUtil;
    private int seconds;
    private int idleID;
    private boolean isIdling;
    private  boolean isRunning;
    private static final int IDLE_TIME = 15, COUNTDOWN_TIME = 90;

    public LobbyCountdown(GameStateUtil gameStateUtil){
        this.gameStateUtil = gameStateUtil;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        stopIdle();
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateUtil.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch(seconds){
                    case 90: case 60: case 30: case 15: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(gameStateUtil.getPlugin().getPrefix()+"§7Das Spiel startet in §e"+seconds+" Sekunden§7.");
                        break;

                    case 1:
                        Bukkit.broadcastMessage(gameStateUtil.getPlugin().getPrefix()+"§7Das Spiel startet in §eeiner Sekunden§7.");
                        break;

                    case 0:
                        gameStateUtil.setGameState(GameState.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        },0, 20);
    }

    @Override
    public void stop() {
        if(isRunning){
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle(){
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateUtil.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(gameStateUtil.getPlugin().getPrefix()+"§7Bis zum Spielstart fehlen noch §e"+
                        (WaitingState.MIN_PLAYERS-gameStateUtil.getPlugin().getIngamePlayers().size())+" §7Spieler");
            }
        },0, 20*IDLE_TIME);
    }

    public void stopIdle(){
        if(isIdling){
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
