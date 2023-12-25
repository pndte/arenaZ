package me.pndte.arenaz.core.listeners;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        if (interactEvent.getAction() != Action.PHYSICAL) return;
        if (!_arenaInfo.containPlate(interactEvent.getClickedBlock())) return;
        var actionPlate = _arenaInfo.getActionPlate(interactEvent.getClickedBlock());
        actionPlate.executeAction(_arenaInfo.getPlayer(interactEvent.getPlayer().getUniqueId())); // TODO: архитектурная ошибка, фиксить
    }

    @EventHandler
    public void moveOnRails(PlayerMoveEvent moveEvent){
        if(moveEvent.isCancelled() || moveEvent.getFrom() == moveEvent.getTo()) return;
        var railBlock = moveEvent.getPlayer().getLocation().getBlock();
        if (railBlock.getType() != Material.RAIL && railBlock.getType() != Material.POWERED_RAIL &&
        railBlock.getType() != Material.ACTIVATOR_RAIL && railBlock.getType() != Material.POWERED_RAIL){
            return; // TODO: фиксить
        }
        moveEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 3, false, false));
    }

    @EventHandler
    public void playerDie(PlayerDeathEvent deathEvent){

    }
}
