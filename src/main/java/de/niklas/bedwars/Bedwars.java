package de.niklas.bedwars;

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

    @Override
    public void onEnable(){
        gameStateUtil = new GameStateUtil(this);
        gameStateUtil.setGameState(GameState.WAITING_STATE);

        ingamePlayers = new ArrayList<>();

        registerListener(Bukkit.getPluginManager());

        getServer().getConsoleSender().sendMessage("§cDas Plugin §aBedwars §cvon §aBukkitNews §cwurde gestartet.");
        getServer().getConsoleSender().sendMessage("§eRef. https://github.com/niklasp2209/Bedwars");
    }

    @Override
    public void onDisable(){

    }

    public void registerListener(PluginManager pluginManager){
        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
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
}
