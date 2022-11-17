package de.niklas.bedwars.commands;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.game.maps.Map;
import de.niklas.bedwars.utils.ConfigurationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedwarsCommand implements CommandExecutor {

    private Bedwars plugin;

    public BedwarsCommand(Bedwars plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            if (player.hasPermission("bedwars.setup")) {
                if(args.length == 1 && args[0].equalsIgnoreCase("setlobby")){
                    new ConfigurationUtil(plugin, player.getLocation(), ".Lobby").saveLocation();
                    player.sendMessage(plugin.getPrefix()+"§aDu hast die Lobby gesetzt.");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);

                }else if(args.length == 3 && args[0].equalsIgnoreCase("create")){
                    Map map = new Map(plugin, args[1]);
                    if(!map.exists()){
                        map.create(args[2]);
                        player.sendMessage(plugin.getPrefix()+"§7Die Map §e" +map.getName()+ " §7wurde §aerstellt§7.");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);
                    }else
                        player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert bereits!");
                }else if(args.length == 3 && args[0].equalsIgnoreCase("set")){
                    Map map = new Map(plugin, args[1]);
                    if(map.exists()){
                        if(args[2].equalsIgnoreCase("bronze")){

                        }else if(args[2].equalsIgnoreCase("iron")){

                        }else if(args[2].equalsIgnoreCase("gold")){

                        }
                    }else
                        player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");
                }else if(args.length == 4 && args[0].equalsIgnoreCase("set")){
                    //bedwars set <MAP> <TEAM> spawn/bed
                    //bedwars set <MAP> bronze/iron/gold
                    if(args[3].equalsIgnoreCase("spawn")){
                        Map map = new Map(plugin, args[1]);
                        if(map.exists()){
                            if(args[2].equalsIgnoreCase("Rot")){
                                map.setSpawnLocation(1, player.getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §cRot §7wurde gesetzt.");
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);

                            }else if(args[2].equalsIgnoreCase("Blau")){
                                map.setSpawnLocation(2, player.getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §9Blau §7wurde gesetzt.");
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);

                            }else if(args[2].equalsIgnoreCase("Gelb")){
                                map.setSpawnLocation(3, player.getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §eGelb §7wurde gesetzt.");
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);

                            }else if(args[2].equalsIgnoreCase("Grün")){
                                map.setSpawnLocation(4, player.getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §aGrün §7wurde gesetzt.");
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1L, 1L);
                            }
                        }else
                            player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");

                    }else if(args[3].equalsIgnoreCase("bed")){
                        Map map = new Map(plugin, args[1]);
                        if(map.exists()){
                            if(args[2].equalsIgnoreCase("Rot")){
                                map.setBedLocations(1, player.getTargetBlock(null, 200).getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §cRot §7wurde gesetzt");
                                player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");

                            }else if(args[2].equalsIgnoreCase("Blau")){
                                map.setBedLocations(2, player.getTargetBlock(null, 200).getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §cRot §7wurde gesetzt");
                                player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");

                            }else if(args[2].equalsIgnoreCase("Gelb")){
                                map.setBedLocations(3, player.getTargetBlock(null, 200).getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §cRot §7wurde gesetzt");
                                player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");

                            }else if(args[2].equalsIgnoreCase("Grün")){
                                map.setBedLocations(4, player.getTargetBlock(null, 200).getLocation());
                                player.sendMessage(plugin.getPrefix()+"§7Spawn für §cRot §7wurde gesetzt");
                                player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");
                            }
                        }else
                            player.sendMessage(plugin.getPrefix()+"§cDiese Map existiert nicht!");
                    }else
                        sendInstructions(player);
                }else
                    sendInstructions(player);
            }else
                player.sendMessage(plugin.getNoPerm());
        }
        return false;
    }

    public void sendInstructions(Player player){

    }
}
