package de.niklas.bedwars.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    public static ItemStack createItem(Material id, String name, int ammount, String lore){
        ItemStack item = new ItemStack(id, ammount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> list = new ArrayList<>();
        list.add(lore);
        meta.setLore(list);
        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack createItemLore(Material id, String name, int ammount, List<String> lore){
        ItemStack item = new ItemStack(id, ammount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> list = new ArrayList<>();
        list.addAll(lore);
        meta.setLore(list);
        item.setItemMeta(meta);
        return item;
    }

    public static void setLobbyItems(Player player){
        ItemStack team = createItem(Material.RED_BED, "§aTeam Auswahl", 1, "§7● Linksklick zum benutzen");
        ItemStack leave = createItem(Material.MAGMA_CREAM, "§cZurück zur Lobby", 1, "§7● Linksklick zum benutzen");

        player.getInventory().clear();
        player.setHealth(20);
        player.getInventory().setItem(0, team);
        player.getInventory().setItem(8, leave);
    }
}
