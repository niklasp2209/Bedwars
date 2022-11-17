package de.niklas.bedwars.game.team;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.gamestate.WaitingState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameTeamListener implements Listener {

    private Bedwars plugin;

    public GameTeamListener(Bedwars plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void handleSelectTeam(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player))return;
        if(!(plugin.getGameStateUtil().getCurrentGameState() instanceof WaitingState))return;
        if(event.getCurrentItem() == null)return;
        if(event.getView().getTitle().equals("§7Team auswählen")){
            Player player = (Player)event.getWhoClicked();
            event.setCancelled(true);

            if(plugin.getGameTeamManager().getTEAM_COUNT() == 4){
                Material material = event.getCurrentItem().getType();

                if(material.equals(Material.RED_WOOL))
                plugin.getGameTeamManager().onTeamSelection(player, plugin.getMap().getTeamRed());

                if(material.equals(Material.YELLOW_WOOL))
                plugin.getGameTeamManager().onTeamSelection(player, plugin.getMap().getTeamYellow());

                if(material.equals(Material.BLUE_WOOL))
                plugin.getGameTeamManager().onTeamSelection(player, plugin.getMap().getTeamBlue());

                if(material.equals(Material.GREEN_WOOL))
                plugin.getGameTeamManager().onTeamSelection(player, plugin.getMap().getTeamGreen());
            }
        }
    }

    @EventHandler
    public void handleOpenTeamInventory(PlayerInteractEvent event){
        if(event.getItem() == null)return;
        if(!(plugin.getGameStateUtil().getCurrentGameState() instanceof  WaitingState))return;
        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))return;
        if(event.getItem().getType() == Material.RED_BED){
            if(event.getItem().getItemMeta().getDisplayName().equals("§aTeam Auswahl")){
                Player player = event.getPlayer();
                plugin.getGameTeamManager().openInventory(player);
            }
        }
    }
}
