package de.niklas.bedwars.countdowns;

import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.gamestate.GameStateUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemCountdown extends Countdown {

    private  boolean isRunning;
    private int seconds;
    private GameStateUtil gameStateUtil;
    private Map map;

    public ItemCountdown(GameStateUtil gameStateUtil){
        this.gameStateUtil = gameStateUtil;
        seconds = 31;
    }

    @Override
    public void start() {
        map = gameStateUtil.getPlugin().getMap();
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateUtil.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (seconds){
                    case 31:
                        for(Location location : map.getGoldLocation()){
                            Bukkit.getWorld("world").dropItemNaturally(location,
                                    new ItemStack(new ItemStack(Material.GOLD_INGOT)));
                        }
                        break;

                    case 30: case 15:
                        for(Location location : map.getIronLocation()){
                            Bukkit.getWorld("world").dropItemNaturally(location,
                                    new ItemStack(new ItemStack(Material.IRON_INGOT)));
                        }
                        break;

                    default:
                        break;
                }
                for(Location location : map.getBronzeLocation()){
                    Bukkit.getWorld("world").dropItemNaturally(location,
                            new ItemStack(Material.BRICK));
                }
                seconds--;
            }
        }, 0, 40);
    }

    @Override
    public void stop() {

    }
}
