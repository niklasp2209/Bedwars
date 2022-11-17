package de.niklas.bedwars.gamestate;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.countdowns.ItemCountdown;
import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.game.team.GameTeam;
import de.niklas.bedwars.game.team.GameTeamManager;
import de.niklas.bedwars.game.team.GameTeamType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class IngameState extends GameState {
    private Map map;
    private Bedwars plugin;
    private ItemCountdown itemCountdown;

    public IngameState(GameStateUtil gameStateUtil){
        this.plugin = JavaPlugin.getPlugin(Bedwars.class);
        itemCountdown = new ItemCountdown(gameStateUtil);
    }

    @Override
    public void start() {
        map = plugin.getMap();
        for(Player player : plugin.getIngamePlayers()){
            if(plugin.getGameTeamManager().getPlayerTeam(player).getGameTeamType().equals(GameTeamType.RED))
                player.teleport(map.getTeamRed().getSpawnLocation());
            else if(plugin.getGameTeamManager().getPlayerTeam(player).getGameTeamType().equals(GameTeamType.BLUE))
                player.teleport(map.getTeamBlue().getSpawnLocation());
            else if(plugin.getGameTeamManager().getPlayerTeam(player).getGameTeamType().equals(GameTeamType.YELLOW))
                player.teleport(map.getTeamYellow().getSpawnLocation());
            else if(plugin.getGameTeamManager().getPlayerTeam(player).getGameTeamType().equals(GameTeamType.GREEN))
                player.teleport(map.getTeamGreen().getSpawnLocation());

            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1L, 1L);
        }
        itemCountdown.startSpawningItems();


    }

    @Override
    public void stop() {

    }
}
