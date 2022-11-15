package de.niklas.bedwars.team;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Teams {
    RED("Rot", ChatColor.RED, Material.RED_WOOL),
    BLUE("Blau", ChatColor.BLUE, Material.BLUE_WOOL),
    YELLOW("Gelb", ChatColor.YELLOW, Material.YELLOW_WOOL),
    GREEN("Grün", ChatColor.GREEN, Material.GREEN_WOOL);

    private String teamName;
    private ChatColor chatcolor;
    private Material material;
    private ArrayList<Player> teamPlayers;

    private Teams(String teamName, ChatColor chatcolor, Material material) {
        this.teamName = teamName;
        this.chatcolor = chatcolor;
        this.material = material;
        teamPlayers = new ArrayList<>();
    }

    public String getTeamName() {
        return chatcolor + teamName;
    }
    public ChatColor getChatcolor() {
        return chatcolor;
    }
    public Material getMaterial() {
        return material;
    }
    public ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }
}
