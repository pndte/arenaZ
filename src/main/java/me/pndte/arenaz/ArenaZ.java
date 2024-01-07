package me.pndte.arenaz;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.commands.actionPlates.SetActionPlateCommand;
import me.pndte.arenaz.core.database.Database;
import me.pndte.arenaz.core.listeners.PlayerListener;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ArenaZ extends JavaPlugin {
    public Database database;
    @Override
    public void onEnable() {
        database = new Database("game_class_action_plates");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        for (var player : getServer().getOnlinePlayers()){
            ArenaInfo.addPlayer(new DefaultArenaPlayer(player));
            if (player.getGameMode() == GameMode.CREATIVE) continue;
            player.getInventory().clear();
            player.damage(Integer.MAX_VALUE);
        }
        new SetActionPlateCommand(this, "setactionplate");
        for (var plate : database.readActionPlates(getServer().getWorld("Selling xonotic for free"))){
            ArenaInfo.addActionPlate(plate);
        }
        System.out.println("Started");
    }

    @Override
    public void onDisable() {
        if(Files.exists(Path.of("plugins/arenaZData/.db")))
            database.clearPlatesTable();
        ArenaInfo.SavePlates(database);
    }
}
