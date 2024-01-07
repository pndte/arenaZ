package me.pndte.arenaz.core.listeners;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {
    private JavaPlugin _plugin;

    public PlayerListener(JavaPlugin plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent joinEvent) {
        ArenaInfo.addPlayer(new DefaultArenaPlayer(joinEvent.getPlayer()));
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent quitEvent) {
        var playerId = quitEvent.getPlayer().getUniqueId();
        if (!ArenaInfo.containPlayer(playerId)) return;
        ArenaInfo.removePlayer(playerId);
    }

    @EventHandler
    public void stepOnPressurePlate(PlayerInteractEvent interactEvent) {
        if (interactEvent.getAction() != Action.PHYSICAL) return;
        if (!ArenaInfo.containPlate(interactEvent.getClickedBlock())) return;
        var actionPlate = ArenaInfo.getActionPlate(interactEvent.getClickedBlock());
        actionPlate.executeAction(ArenaInfo.getPlayer(interactEvent.getPlayer().getUniqueId())); // TODO: архитектурная ошибка, фиксить
    }

    @EventHandler
    public void moveOnRails(PlayerMoveEvent moveEvent) {
        if (moveEvent.isCancelled() || moveEvent.getFrom() == moveEvent.getTo()) return;
        var railBlock = moveEvent.getPlayer().getLocation().getBlock();
        if (railBlock.getType() != Material.RAIL && railBlock.getType() != Material.POWERED_RAIL &&
                railBlock.getType() != Material.ACTIVATOR_RAIL && railBlock.getType() != Material.POWERED_RAIL) {
            return; // TODO: фиксить
        }
        moveEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3, 3, false, false));
    }

    @EventHandler
    public void die(PlayerDeathEvent deathEvent) {
        var deadPlayer = ArenaInfo.getPlayer(deathEvent.getEntity().getUniqueId());
        deadPlayer.playerStatistics().deathCount.Increase();

        deadPlayer.minecraftPlayer().setPlayerListName(deadPlayer.minecraftPlayer().getName() + ChatColor.RED + " K: " +
                +deadPlayer.playerStatistics().killCount.matchValue() + ChatColor.GRAY + " | D: " + deadPlayer.playerStatistics().deathCount.matchValue());

        if (deathEvent.getEntity().getKiller() == null) return;
        var killerPlayer = ArenaInfo.getPlayer(deathEvent.getEntity().getKiller().getUniqueId());
        killerPlayer.playerStatistics().killCount.Increase();

        killerPlayer.minecraftPlayer().setPlayerListName(killerPlayer.minecraftPlayer().getName() + ChatColor.RED + " K: " +
                +killerPlayer.playerStatistics().killCount.matchValue() + ChatColor.GRAY + " | D: " + killerPlayer.playerStatistics().deathCount.matchValue());

        if (killerPlayer.playerStatistics().killCount.matchValue() >= 15) {
            for (var player : ArenaInfo.players.values()) {
                player.minecraftPlayer().sendTitle(killerPlayer.minecraftPlayer().getName(), "одержал победу!");
                player.playerStatistics().killCount.ClearMatchValue(0);
                player.playerStatistics().deathCount.ClearMatchValue(0);
                player.minecraftPlayer().setPlayerListName(player.minecraftPlayer().getName() + ChatColor.RED + " K: " +
                        +player.playerStatistics().killCount.matchValue() + ChatColor.GRAY + " | D: " + player.playerStatistics().deathCount.matchValue());
            }
        }

    }

    @EventHandler
    public void drop(PlayerDropItemEvent dropItemEvent) {
        if (dropItemEvent.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        dropItemEvent.setCancelled(true);
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent respawnEvent) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(_plugin, () -> {
            respawnEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            respawnEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 99, false, false));
        }, 1);
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent clickEvent){
        if(clickEvent.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        clickEvent.setCancelled(true);
    }
}
