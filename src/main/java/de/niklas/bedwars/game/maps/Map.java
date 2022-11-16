package de.niklas.bedwars.game.maps;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.game.team.GameTeam;
import de.niklas.bedwars.game.team.GameTeamManager;
import de.niklas.bedwars.game.team.GameTeamType;
import de.niklas.bedwars.utils.ConfigurationUtil;
import de.niklas.bedwars.utils.LocationUtil;
import org.bukkit.Location;

public class Map {
    /*
    1 = ROT
    2 = BLAU
    3 = GELB
    4 = GRÜN
     */

    private Bedwars plugin;
    private String name;
    private String builder;
    private Location[] spawnLocations;
    private Location[] bedLocations;
    private Location spectatorLocation;

    private GameTeam teamRed;
    private GameTeam teamGreen;
    private GameTeam teamBlue;
    private GameTeam teamYellow;

    public Map(Bedwars plugin, String name){
        this.plugin = plugin;
        this.name = name.toUpperCase();
        this.spawnLocations = new Location[plugin.getGameTeamManager().getTEAM_COUNT()];

        if(spawnLocations.length == 4) {
            teamRed = GameTeam.create(GameTeamType.RED, null, null);
            teamBlue = GameTeam.create(GameTeamType.BLUE, null, null);
            teamYellow = GameTeam.create(GameTeamType.YELLOW, null, null);
            teamGreen = GameTeam.create(GameTeamType.GREEN, null, null);
        }
    }

    public void setSpawnLocation(int spawnNumber, Location location){
        spawnLocations[spawnNumber-1] = location;
        new ConfigurationUtil(plugin, location, "Maps."+name+"."+spawnNumber).saveLocation();
    }

    public void create(String builder){
        this.builder = builder;
        plugin.getConfig().set("Maps."+name+".Builder", builder);
        plugin.saveConfig();
    }

    public boolean exists(){
        return (plugin.getConfig().getString("Maps." +name+ ".Builder") != null);
    }

    public void load(){

    }//        gameTeam = GameTeam.create(GameTeamType.BLUE, loc1, loc2);


    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    public Location[] getSpawnLocations() {
        return spawnLocations;
    }

    public String getBuilder() {
        return builder;
    }

    public String getName() {
        return name;
    }
}
