package de.niklas.bedwars.listener;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.countdowns.LobbyCountdown;
import de.niklas.bedwars.game.team.GameTeam;
import de.niklas.bedwars.game.team.GameTeamManager;
import de.niklas.bedwars.game.team.GameTeamType;
import de.niklas.bedwars.gamestate.IngameState;
import de.niklas.bedwars.gamestate.WaitingState;
import de.niklas.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private Bedwars plugin;
    private GameTeam gameTeam;

    public PlayerConnectionListener(Bedwars plugin){
        this.plugin = plugin;

//        Location loc1 = Bukkit.getWorld("world").getSpawnLocation();
//        Location loc2 = Bukkit.getWorld("world").getSpawnLocation();
//        gameTeam = GameTeam.create(GameTeamType.BLUE, loc1, loc2);

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void handleJoin(PlayerJoinEvent event){
        if(plugin.getGameStateUtil().getCurrentGameState() instanceof WaitingState){
            Player player = event.getPlayer();
            plugin.getIngamePlayers().add(player);
            event.setJoinMessage(plugin.getPrefix()+"§a"+player.getName()+" §7ist dem Spiel §abeigetreten§7 [§a"+
                    plugin.getIngamePlayers().size()+"§7/§c"+WaitingState.MAX_PLAYERS+"§7]");

            WaitingState waitingState = (WaitingState) plugin.getGameStateUtil().getCurrentGameState();
            LobbyCountdown lobbyCountdown = waitingState.getLobbyCountdown();
            if(plugin.getIngamePlayers().size() >= WaitingState.MIN_PLAYERS) {
                if (!lobbyCountdown.isRunning())
                    lobbyCountdown.start();
            }

            ItemBuilder.setLobbyItems(player);

        }else if(plugin.getGameStateUtil().getCurrentGameState() instanceof IngameState) {
            event.setJoinMessage(null);
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void handleQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(plugin.getIngamePlayers().contains(player))
            plugin.getIngamePlayers().remove(player);

        if(plugin.getGameStateUtil().getCurrentGameState() instanceof WaitingState){
            event.setQuitMessage(plugin.getPrefix()+"§a"+player.getName()+" §7hat das Spiel §cverlassen§7 [§a"+
                    plugin.getIngamePlayers().size()+"§7/§c"+WaitingState.MAX_PLAYERS+"§7]");

            WaitingState waitingState = (WaitingState) plugin.getGameStateUtil().getCurrentGameState();
            LobbyCountdown lobbyCountdown = waitingState.getLobbyCountdown();
            if(plugin.getIngamePlayers().size() < WaitingState.MIN_PLAYERS){
                if(lobbyCountdown.isRunning()){
                    lobbyCountdown.stop();
                    lobbyCountdown.startIdle();
                }
            }

        }else if(plugin.getGameStateUtil().getCurrentGameState() instanceof IngameState){
            event.setQuitMessage(null);
        }
    }
}
