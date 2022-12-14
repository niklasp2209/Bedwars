package de.niklas.bedwars;

import de.niklas.bedwars.commands.BedwarsCommand;
import de.niklas.bedwars.commands.StartCommand;
import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.game.team.GameTeamListener;
import de.niklas.bedwars.game.team.GameTeamManager;
import de.niklas.bedwars.gamestate.GameState;
import de.niklas.bedwars.gamestate.GameStateUtil;
import de.niklas.bedwars.listener.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Bedwars extends JavaPlugin {

    private final String prefix = "§eBedwars §7● §r",
            noPerm = prefix+"§cDu hast leider nicht genügend Rechte!";

    private ArrayList<Player> ingamePlayers;

    private GameStateUtil gameStateUtil;
    private GameTeamManager gameTeamManager;
    private Map map;

    @Override
    public void onEnable(){
        gameStateUtil = new GameStateUtil(this);
        gameTeamManager = new GameTeamManager(this);
        gameStateUtil.setGameState(GameState.WAITING_STATE);

        ingamePlayers = new ArrayList<>();

        registerListener(Bukkit.getPluginManager());
        registerCommands();

        getServer().getConsoleSender().sendMessage("§cDas Plugin §aBedwars §cvon §aBukkitNews §cwurde gestartet.");
        getServer().getConsoleSender().sendMessage("§eRef. https://github.com/niklasp2209/Bedwars");

        map = new Map(this, "Haus");
        map.load();
    }

    @Override
    public void onDisable(){

    }

    public void registerListener(PluginManager pluginManager){
        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
        pluginManager.registerEvents(new GameTeamListener(this), this);
    }

    public void registerCommands(){
        this.getCommand("bedwars").setExecutor(new BedwarsCommand(this));
        this.getCommand("start").setExecutor(new StartCommand(this));
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoPerm() {
        return noPerm;
    }

    public ArrayList<Player> getIngamePlayers() {
        return ingamePlayers;
    }

    public GameStateUtil getGameStateUtil() {
        return gameStateUtil;
    }

    public GameTeamManager getGameTeamManager() {
        return gameTeamManager;
    }

    public Map getMap() {
        return map;
    }
}
