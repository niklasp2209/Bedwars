package de.niklas.bedwars.countdowns;

import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.gamestate.GameStateUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class ItemCountdown {
    private GameStateUtil gameStateUtil;
    private Map map;

    public ItemCountdown(GameStateUtil gameStateUtil){
        this.gameStateUtil = gameStateUtil;
    }

    public void startSpawningItems(){
        map = gameStateUtil.getPlugin().getMap();
        map.getItemSpawners()
                .keySet()
                .stream()
                .forEach(gameSpawner -> Bukkit.getScheduler().runTaskTimer(gameStateUtil.getPlugin(),
                        () -> Arrays.stream(map.getItemSpawners().get(gameSpawner))
                        .forEach(location -> {
                            Item item = location.getWorld().dropItemNaturally(location, gameSpawner.getItemStack());
                            item.setVelocity(new Vector(0, 0, 0));
                        }), 0, gameSpawner.getPeriod()));
    }
}
