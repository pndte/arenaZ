package me.pndte.arenaz.core.listeners;

import me.pndte.arenaz.core.ArenaInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ActionPlatesListener implements Listener {
    @EventHandler
    public void plateRemoved(BlockBreakEvent breakEvent){
        if (ArenaInfo.containPlate(breakEvent.getBlock())) ArenaInfo.removePlate(breakEvent.getBlock());
    }
}
