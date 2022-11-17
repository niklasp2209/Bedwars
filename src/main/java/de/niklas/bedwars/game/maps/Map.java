package de.niklas.bedwars.game.maps;

import de.niklas.bedwars.Bedwars;
import de.niklas.bedwars.game.items.GameSpawner;
import de.niklas.bedwars.game.team.GameTeam;
import de.niklas.bedwars.game.team.GameTeamType;
import de.niklas.bedwars.utils.ConfigurationUtil;
import org.bukkit.Location;

import java.util.HashMap;

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
    private Location[] bronzeLocation;
    private Location[] ironLocation;
    private Location[] goldLocation;
    private Location spectatorLocation;

    private HashMap<GameSpawner, Location[]> itemSpawners;

    private GameTeam teamRed;
    private GameTeam teamGreen;
    private GameTeam teamBlue;
    private GameTeam teamYellow;

    public Map(Bedwars plugin, String name){
        this.plugin = plugin;
        this.name = name.toUpperCase();
        this.spawnLocations = new Location[plugin.getGameTeamManager().getTEAM_COUNT()];
        this.bedLocations = new Location[plugin.getGameTeamManager().getTEAM_COUNT()];
        this.bronzeLocation = new Location[4];
        this.ironLocation = new Location[4];
        this.goldLocation = new Location[4];
        itemSpawners = new HashMap<>();

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

    public void setBronzeLocation(int bronzeNumber, Location location){
        bronzeLocation[bronzeNumber-1] = location;
        new ConfigurationUtil(plugin, location, "Maps."+name+".Bronze"+"."+bronzeNumber).saveLocation();
    }

    public void setIronLocation(int ironNumber, Location location){
        ironLocation[ironNumber-1] = location;
        new ConfigurationUtil(plugin, location, "Maps."+name+".Iron"+"."+ironNumber).saveLocation();
    }

    public void setGoldLocation(int goldNumber, Location location){
        goldLocation[goldNumber-1] = location;
        new ConfigurationUtil(plugin, location, "Maps."+name+".Gold"+"."+goldNumber).saveLocation();
    }
    public void setBedLocations(int bedNumber, Location location){
        bedLocations[bedNumber-1] = location;
        new ConfigurationUtil(plugin, location, "Maps."+name+".Bed"+"."+bedNumber).saveLocation();
    }

    public void create(String builder){
        this.builder = builder;
        plugin.getConfig().set("Maps."+name+".Builder", builder);
        plugin.saveConfig();
    }

    public boolean playable(){
        return false;
    }

    public boolean exists(){
        return (plugin.getConfig().getString("Maps." +name+ ".Builder") != null);
    }

    public void load(){
        Location spawnLocationRed = new ConfigurationUtil(plugin, "Maps."+name+".1").loadLocation();
        Location bedLocationRed = new ConfigurationUtil(plugin, "Maps."+name+".Bed"+".1").loadLocation();
        teamRed.setSpawnLocation(spawnLocationRed);
        teamRed.setBedLocation(bedLocationRed);


        Location spawnLocationBlue = new ConfigurationUtil(plugin, "Maps."+name+".2").loadLocation();
        Location bedLocationBlue = new ConfigurationUtil(plugin, "Maps."+name+".Bed"+".1").loadLocation();
        teamBlue.setSpawnLocation(spawnLocationBlue);
        teamBlue.setBedLocation(bedLocationBlue);


        Location spawnLocationYellow = new ConfigurationUtil(plugin, "Maps."+name+".3").loadLocation();
        Location bedLocationYellow = new ConfigurationUtil(plugin, "Maps."+name+".Bed"+".1").loadLocation();
        teamYellow.setSpawnLocation(spawnLocationYellow);
        teamYellow.setBedLocation(bedLocationYellow);


        Location spawnLocationGreen = new ConfigurationUtil(plugin, "Maps."+name+".4").loadLocation();
        Location bedLocationGreen = new ConfigurationUtil(plugin, "Maps."+name+".Bed"+".1").loadLocation();
        teamGreen.setSpawnLocation(spawnLocationGreen);
        teamGreen.setBedLocation(bedLocationGreen);

        for (int i = 0; i < 4; i++) {
            Location bronzeLocation = new ConfigurationUtil(plugin, "Maps."+name+".Bronze"+"."+(i+1)).loadLocation();
            setBronzeLocation(i+1, bronzeLocation);

            Location ironLocation = new ConfigurationUtil(plugin, "Maps."+name+".Iron"+"."+(i+1)).loadLocation();
            setIronLocation(i+1, ironLocation);

            Location goldLocation = new ConfigurationUtil(plugin, "Maps."+name+".Gold"+"."+(i+1)).loadLocation();
            setGoldLocation(i+1, goldLocation);

            itemSpawners.put(GameSpawner.BRONZE, getBronzeLocation());
            itemSpawners.put(GameSpawner.IRON, getIronLocation());
            itemSpawners.put(GameSpawner.GOLD, getGoldLocation());

        }
    }


    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    public HashMap<GameSpawner, Location[]> getItemSpawners() {
        return itemSpawners;
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

    public GameTeam getTeamGreen() {
        return teamGreen;
    }

    public GameTeam getTeamRed() {
        return teamRed;
    }

    public GameTeam getTeamBlue() {
        return teamBlue;
    }

    public GameTeam getTeamYellow() {
        return teamYellow;
    }

    public Location[] getBedLocations() {
        return bedLocations;
    }

    public Location[] getBronzeLocation() {
        return bronzeLocation;
    }

    public Location[] getGoldLocation() {
        return goldLocation;
    }

    public Location[] getIronLocation() {
        return ironLocation;
    }
}
