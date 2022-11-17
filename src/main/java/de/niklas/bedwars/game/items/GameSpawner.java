package de.niklas.bedwars.game.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GameSpawner {
    BRONZE("Bronze", new ItemStack(Material.BRICK), 3*20),
    IRON("Eisen", new ItemStack(Material.IRON_INGOT), 30*20),
    GOLD("Gold", new ItemStack(Material.GOLD_INGOT), 60*20);

    private ItemStack itemStack;
    private int period;
    private String itemName;

    private GameSpawner(String itemName, ItemStack itemStack, int period){
        this.itemName = itemName;
        this.itemStack = itemStack;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
