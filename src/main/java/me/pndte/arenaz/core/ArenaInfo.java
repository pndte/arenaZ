package me.pndte.arenaz.core;
import me.pndte.arenaz.core.action_blocks.IActionPlate;
import me.pndte.arenaz.core.database.Database;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.UUID;

public class ArenaInfo {
    public static final HashMap<UUID, DefaultArenaPlayer> players = new HashMap<>();
    public static final HashMap<Block, IActionPlate> actionPlates = new HashMap<>();

    public static void addPlayer(DefaultArenaPlayer player){
        players.put(player.minecraftPlayer().getUniqueId(), player);
    }
    public static DefaultArenaPlayer getPlayer(UUID playerId){
        return players.get(playerId);
    }

    public static void removePlayer(UUID playerId){
        players.remove(playerId);
    }

    public static boolean containPlayer(UUID playerId){
        return players.containsKey(playerId);
    }

    public static void addActionPlate(IActionPlate actionPlate){
        actionPlates.put(actionPlate.plate(), actionPlate);
    }

    public static IActionPlate getActionPlate(Block plate){
        return actionPlates.get(plate);
    }

    public static void removePlate(Block block){
        actionPlates.remove(block);
    }

    public static boolean containPlate(Block block){
        return actionPlates.containsKey(block);
    }

    public static void SavePlates(Database database){
        database.WriteActionPlates(actionPlates.values());
    }
}
