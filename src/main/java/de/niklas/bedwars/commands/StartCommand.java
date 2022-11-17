package de.niklas.bedwars.commands;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.countdowns.LobbyCountdown;
import de.niklas.bedwars.gamestate.WaitingState;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private Bedwars plugin;
    private int secondsStart = 5;

    public StartCommand(Bedwars plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(player.hasPermission("bedwars.start")){
                if(plugin.getGameStateUtil().getCurrentGameState() instanceof WaitingState){
                    WaitingState waitingState = (WaitingState) plugin.getGameStateUtil().getCurrentGameState();
                    if(waitingState.getLobbyCountdown().getSeconds() >= secondsStart && waitingState.getLobbyCountdown().isRunning()){
                        waitingState.getLobbyCountdown().setSeconds(secondsStart);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);
                        player.sendMessage(plugin.getPrefix()+"§cDu hast den Lobby Countdown verkürzt");
                    }else
                        player.sendMessage(plugin.getPrefix()+"§cDie Runde startet bereits!");
                }else
                    player.sendMessage(plugin.getPrefix()+"§cDu Runde hat bereits gestartet!");
            }else
                player.sendMessage(plugin.getNoPerm());
        }
        return false;
    }
}
