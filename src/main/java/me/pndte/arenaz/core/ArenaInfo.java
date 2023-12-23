package me.pndte.arenaz.core;
import me.pndte.arenaz.core.action_blocks.IActionPlate;
import me.pndte.arenaz.core.players.IArenaPlayer;
import org.bukkit.block.Block;
import java.util.HashMap;
import java.util.UUID;

public class ArenaInfo {
    public final HashMap<UUID, IArenaPlayer> _players = new HashMap<>();
    private final HashMap<Block, IActionPlate> _actionPlates = new HashMap<>();

    public void addPlayer(IArenaPlayer player){
        _players.put(player.minecraftPlayer().getUniqueId(), player);
    }

    public void removePlayer(UUID playerId){
        _players.remove(playerId);
    }

    public boolean containPlayer(UUID playerId){
        return _players.containsKey(playerId);
    }

    public void addActionPlate(IActionPlate actionPlate){
        _actionPlates.put(actionPlate.plate(), actionPlate);
    }

    public void removePlate(Block block){
        _actionPlates.remove(block);
    }

    public boolean containPlate(Block block){
        return _actionPlates.containsKey(block);
    }
}
