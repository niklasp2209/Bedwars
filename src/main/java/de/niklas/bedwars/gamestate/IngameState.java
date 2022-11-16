package de.niklas.bedwars.gamestate;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.game.team.GameTeam;
import de.niklas.bedwars.game.team.GameTeamType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class IngameState extends GameState {
    private Map map;
    private Bedwars plugin;

    public IngameState(GameStateUtil gameStateUtil){
        this.plugin = JavaPlugin.getPlugin(Bedwars.class);
    }

    @Override
    public void start() {
        map.load();

        
    }

    @Override
    public void stop() {

    }
}
