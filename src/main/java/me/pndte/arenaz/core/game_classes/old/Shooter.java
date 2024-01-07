package me.pndte.arenaz.core.game_classes.old;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.events.DefaultEventTarget;
import me.pndte.arenaz.core.game_classes.DefaultArenaClass;
import me.pndte.arenaz.core.game_classes.DefaultArmored;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.other.ArmorSet;
import me.pndte.arenaz.core.other.UnbreakableItemStack;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Shooter extends DefaultArenaClass {
    @Override
    public void applyClass(Player player) {
        var arenaPlayer = ArenaInfo.getPlayer(player.getUniqueId());

        onKill = new DefaultEventTarget<>(args -> {
            doOnKill(args, arenaPlayer);
        });
        onDeath = new DefaultEventTarget<>(args -> player.getInventory().clear());
        arenaPlayer.playerStatistics().killCount.matchValueChanged.add(onKill);
        arenaPlayer.playerStatistics().deathCount.matchValueChanged.add(onDeath);

        putOnArmor(player);

        var crossbow = new UnbreakableItemStack(Material.CROSSBOW);
        crossbow.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
        crossbow.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);

        player.getInventory().setItemInOffHand(crossbow);
        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 10));
        player.getInventory().setItem(8, new ItemStack(Material.ARROW, 24));
        player.getInventory().setItem(0, new UnbreakableItemStack(Material.WOODEN_SWORD));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));

    }
    @Override
    public void doOnKill(int KillCount, DefaultArenaPlayer arenaPlayer){
        arenaPlayer.minecraftPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 1,
                false, false));
        arenaPlayer.minecraftPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 6));
        arenaPlayer.minecraftPlayer().getInventory().addItem(new UnbreakableItemStack(Material.ARROW, 24));
    }

    @Override
    public void putOnArmor(Player player) {
        defaultArmored = new DefaultArmored(new ArmorSet(new UnbreakableItemStack(Material.LEATHER_HELMET),
                new UnbreakableItemStack(Material.IRON_CHESTPLATE), new UnbreakableItemStack(Material.LEATHER_LEGGINGS),
                new UnbreakableItemStack(Material.LEATHER_BOOTS)));
        defaultArmored.putOnArmor(player);
    }
}
