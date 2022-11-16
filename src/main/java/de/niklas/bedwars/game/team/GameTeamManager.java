package de.niklas.bedwars.game.team;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.game.GamePlayer;
import de.niklas.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameTeamManager {

    private final Bedwars plugin;
    private final int TEAM_SIZE;
    private final int TEAM_COUNT;
    private HashMap<Player, GameTeam> teamHashMap;

    public GameTeamManager(Bedwars plugin){
        teamHashMap = new HashMap<Player, GameTeam>();
        this.plugin = plugin;
        this.TEAM_SIZE = 2;
        this.TEAM_COUNT = 4;
    }

    public GameTeam getPlayerTeam(Player player){
        return teamHashMap.get(player);
    }

    public void setPlayerTeam(Player player, GameTeam gameTeam) {
        if(getPlayerTeam(player) != null)
            getPlayerTeam(player).getPlayers().remove(player);

        gameTeam.getPlayers().add(player);
        GameTeamType gameTeamType = gameTeam.getGameTeamType();
        gameTeamType.getTeamPlayers().add(player);
        teamHashMap.put(player, gameTeam);
    }

    public void removePlayerTeam(Player player){
        GameTeam gameTeam = getPlayerTeam(player);
        gameTeam.getPlayers().remove(player);
        teamHashMap.remove(player);
    }

//    public void printTeam(Player player){
//        System.out.println(getPlayerTeam(player).getGameTeamType().getTeamName());
//    }

    public void openInventory(Player player){
        player.openInventory(generateTeamInventory());
    }

    private Inventory generateTeamInventory(){
        Inventory inventory = Bukkit.createInventory(null, 9, "§7Team auswählen");

        int i = 0;

        for(GameTeamType gameTeamType : GameTeamType.values()){
            int slot = 0;

            if( i < 4)
                slot = (i*2)+1;

            List<String> lore = new ArrayList<>();

            for(Player player : gameTeamType.getTeamPlayers()){
                lore.add("§7● " + gameTeamType.getChatcolor() + player.getName());
            }

            inventory.setItem(slot, ItemBuilder.createItemLore(gameTeamType.getMaterial(),
                    gameTeamType.getTeamName(), 1, lore));

            i++;
        }

        return inventory;
    }

    public void onTeamSelection(Player player, GameTeam gameTeam){
        setPlayerTeam(player, gameTeam);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);

        GameTeamType gameTeamType = gameTeam.getGameTeamType();
        player.sendMessage(plugin.getPrefix()+"§7Du bist Team "+
                gameTeamType.getChatcolor()+gameTeamType.getTeamName()+" §7beigetreten");
        player.closeInventory();
    }

    public int getTEAM_COUNT() {
        return TEAM_COUNT;
    }
}
