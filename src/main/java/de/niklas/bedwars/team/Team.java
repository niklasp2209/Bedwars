package de.niklas.bedwars.team;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Team {
    RED("Rot", ChatColor.RED, Material.RED_WOOL),
    BLUE("Blau", ChatColor.BLUE, Material.BLUE_WOOL),
    YELLOW("Gelb", ChatColor.YELLOW, Material.YELLOW_WOOL),
    GREEN("Grün", ChatColor.GREEN, Material.GREEN_WOOL);

    private String teamName;
    private ChatColor chatColor;
    private Material material;
    private ArrayList<Player> teamPlayers;

    private Team(String teamName, ChatColor chatColor, Material material){
        this.teamName = teamName;
        this.chatColor = chatColor;
        this.material = material;
        teamPlayers = new ArrayList<>();
    }

    public ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public Material getMaterial() {
        return material;
    }

    public String getTeamName() {
        return teamName;
    }
}
