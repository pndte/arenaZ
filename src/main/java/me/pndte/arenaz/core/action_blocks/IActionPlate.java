package me.pndte.arenaz.core.action_blocks;

import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.block.Block;

public interface IActionPlate {
    public void executeAction(DefaultArenaPlayer player);
    public Block plate();
    public IGameClass gameClass();
}
