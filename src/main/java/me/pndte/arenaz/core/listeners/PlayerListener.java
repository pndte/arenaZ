package me.pndte.arenaz.core.listeners;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final ArenaInfo _arenaInfo;
    public PlayerListener(ArenaInfo info){
        _arenaInfo = info;
    }
    @EventHandler
    public void playerJoin(PlayerJoinEvent joinEvent){
        _arenaInfo.addPlayer(new DefaultArenaPlayer(joinEvent.getPlayer()));
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent quitEvent){
        var playerId = quitEvent.getPlayer().getUniqueId();
        if (!_arenaInfo.containPlayer(playerId)) return;
        _arenaInfo.removePlayer(playerId);
    }

    @EventHandler
    public void stepOnPressurePlate(PlayerInteractEvent interactEvent){
        //if (interactEvent.getAction() == Action.PHYSICAL && )
    }
}
