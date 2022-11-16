package de.niklas.bedwars.game.team;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.gamestate.WaitingState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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

        Player player = (Player)event.getWhoClicked();
        event.setCancelled(true);

        if(plugin.getGameTeamManager().getTEAM_COUNT() == 4){
            Material material = event.getCurrentItem().getType();

            if(material.equals(Material.RED_WOOL));
      //          plugin.getGameTeamManager().onTeamSelection(player, TEAM);
        }
    }
}
