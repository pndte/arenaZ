package me.pndte.arenaz;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.listeners.PlayerListener;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArenaZ extends JavaPlugin {
    public ArenaInfo arenaInfo;
    @Override
    public void onEnable() {
        arenaInfo = new ArenaInfo();
        getServer().getPluginManager().registerEvents(new PlayerListener(arenaInfo), this);
        for (var player : getServer().getOnlinePlayers()){
            arenaInfo.addPlayer(new DefaultArenaPlayer(player));
        }
        System.out.println("Started");
    }

    @Override
    public void onDisable() {
    }
}
