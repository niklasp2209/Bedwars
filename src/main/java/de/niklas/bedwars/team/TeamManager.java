package de.niklas.bedwars.team;

import de.niklas.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TeamManager {

    private HashMap<Player, GameTeam> teamHashMap;

    public TeamManager(){
        teamHashMap = new HashMap<>();
    }

    public Teams getPlayerTeam(Player player){
        return teamHashMap.get(player).getTeams();
    }

    public void setPlayerTeam(Player player, GameTeam gameTeam) {
        if(getPlayerTeam(player) != null)
            getPlayerTeam(player).getTeamPlayers().remove(player);
        gameTeam.getPlayers().add(player);
        teamHashMap.put(player, gameTeam);
    }

    public void removePlayerTeam(Player player) {
        Teams team = getPlayerTeam(player);
        team.getTeamPlayers().remove(player);
        teamHashMap.remove(player);
    }

    private Teams getLeastPlayersTeam() {
        Teams currentLeastPlayerTeam = Teams.values()[0];
        for(Teams current : Teams.values()) {
            if(current.getTeamPlayers().size() < currentLeastPlayerTeam.getTeamPlayers().size())
                currentLeastPlayerTeam = current;
        }
        return currentLeastPlayerTeam;
    }

    private Teams getMostPlayersTeam() {
        Teams currentMostPlayersTeam = Teams.values()[0];
        for(Teams current : Teams.values()) {
            if(current.getTeamPlayers().size() > currentMostPlayersTeam.getTeamPlayers().size())
                currentMostPlayersTeam = current;
        }
        return currentMostPlayersTeam;
    }

    public void openTeamInventory(Player player){
        player.openInventory(generateTeamInventory());
    }

    private Inventory generateTeamInventory(){
        Inventory inventory = Bukkit.createInventory(null, 9, "§7Team auswählen");

        int i = 0;

        for(Teams teams : Teams.values()){
            int slot = 0;

            if(i < 4)
                slot = (i*2)+1;

            List<String> lore = new ArrayList<>();
            List<String> teamMembers = Collections.singletonList(teams.getTeamName().toString());

            inventory.setItem(slot, ItemBuilder.createItem(teams.getMaterial(), teams.getTeamName(), 1, lore.toString()));
        }

        return inventory;
    }
}
