package me.pndte.arenaz.core;
import me.pndte.arenaz.core.action_blocks.IActionPlate;
import me.pndte.arenaz.core.database.Database;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.block.Block;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.UUID;

public class ArenaInfo {
    private final HashMap<UUID, DefaultArenaPlayer> _players = new HashMap<>();
    private final HashMap<Block, IActionPlate> _actionPlates = new HashMap<>();

    public void addPlayer(DefaultArenaPlayer player){
        _players.put(player.minecraftPlayer().getUniqueId(), player);
    }
    public DefaultArenaPlayer getPlayer(UUID playerId){
        return _players.get(playerId);
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

    public IActionPlate getActionPlate(Block plate){
        return _actionPlates.get(plate);
    }

    public void removePlate(Block block){
        _actionPlates.remove(block);
    }

    public boolean containPlate(Block block){
        return _actionPlates.containsKey(block);
    }

    public void SavePlates(Database database){
        database.WriteActionPlates(_actionPlates.values());
    }
}
