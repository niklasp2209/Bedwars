package de.niklas.bedwars.team;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.listener.PlayerConnectionListener;
import de.niklas.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class TeamManager {

    private HashMap<Player, GameTeam> teamHashMap;
    private Inventory inventory;

    public TeamManager(){
        teamHashMap = new HashMap<>();
    }

    public Teams getPlayerTeam(Player player){
        return teamHashMap.containsKey(player) ? teamHashMap.get(player).getTeams() : null;
//       return teamHashMap.get(player).getTeams() ? teamHashMap.get(player) : null;
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
        inventory = Bukkit.createInventory(null, 9, "§7Team auswählen");

        int i = 0;

        for(Teams teams : Teams.values()){
            int slot = 0;

            if(i < 4)
                slot = (i*2)+1;

            List<String> lore = new ArrayList<>();
            List<String> teamMembers = PlayerConnectionListener.gameTeam.getPlayers()
                    .stream()
                    .filter(gamePlayer -> teams.getTeamName() != null)
                    .filter(gamePlayer -> getPlayerTeam(gamePlayer) == teams)
                    .map(gamePlayer -> gamePlayer.getPlayer().getName())
                    .collect(Collectors.toList());

            if (!teamMembers.isEmpty()) {
                for (String name : teamMembers) {
                    lore.add("§7➥ " + teams.getChatcolor() + name);
                }
            }

            inventory.setItem(slot, ItemBuilder.createItem(teams.getMaterial(), teams.getTeamName(), 1, lore.toString()));

            i++;
        }
        return inventory;
    }
}
