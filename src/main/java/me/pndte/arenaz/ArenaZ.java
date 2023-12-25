package me.pndte.arenaz;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.commands.actionPlates.SetActionPlateCommand;
import me.pndte.arenaz.core.database.Database;
import me.pndte.arenaz.core.listeners.PlayerListener;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ArenaZ extends JavaPlugin {
    public ArenaInfo arenaInfo;
    public Database database;
    @Override
    public void onEnable() {
        database = new Database("game_class_action_plates");
        arenaInfo = new ArenaInfo();
        getServer().getPluginManager().registerEvents(new PlayerListener(arenaInfo), this);
        for (var player : getServer().getOnlinePlayers()){
            arenaInfo.addPlayer(new DefaultArenaPlayer(player));
        }
        new SetActionPlateCommand(this, "setactionplate", arenaInfo);
        for (var plate : database.readActionPlates(getServer().getWorld("Selling xonotic for free"))){
            arenaInfo.addActionPlate(plate);
        }
        System.out.println("Started");
    }

    @Override
    public void onDisable() {
        if(Files.exists(Path.of("plugins/arenaZData/.db")))
            database.clearPlatesTable();
        arenaInfo.SavePlates(database);
    }
}
