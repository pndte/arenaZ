package me.pndte.arenaz.core.action_blocks;

import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import me.pndte.arenaz.core.players.IArenaPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface IActionPlate {
    public void executeAction(DefaultArenaPlayer player);
    public Block plate();
}
