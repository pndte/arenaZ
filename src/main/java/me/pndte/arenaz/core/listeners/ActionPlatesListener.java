package me.pndte.arenaz.core.listeners;

import me.pndte.arenaz.core.ArenaInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ActionPlatesListener implements Listener {
    private final ArenaInfo _arenaInfo;
    public ActionPlatesListener(ArenaInfo arenaInfo){
        _arenaInfo = arenaInfo;
    }
    @EventHandler
    public void plateRemoved(BlockBreakEvent breakEvent){
        if (_arenaInfo.containPlate(breakEvent.getBlock())) _arenaInfo.removePlate(breakEvent.getBlock());
    }
}
