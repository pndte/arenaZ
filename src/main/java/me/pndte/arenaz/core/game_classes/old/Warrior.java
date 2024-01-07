package me.pndte.arenaz.core.game_classes.old;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.events.DefaultEventTarget;
import me.pndte.arenaz.core.events.IEventTarget;
import me.pndte.arenaz.core.game_classes.DefaultArenaClass;
import me.pndte.arenaz.core.game_classes.DefaultArmored;
import me.pndte.arenaz.core.game_classes.absctractions.IArmored;
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

public class Warrior extends DefaultArenaClass {

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

        var shield = new UnbreakableItemStack(Material.SHIELD);
        shield.addUnsafeEnchantment(Enchantment.KNOCKBACK, 6);
        player.getInventory().setItem(0, new UnbreakableItemStack(Material.DIAMOND_SWORD));
        player.getInventory().setItemInOffHand(shield);
        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 12));
        player.getInventory().setItem(8, new UnbreakableItemStack(Material.WOODEN_AXE, 1));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
    }
    @Override
    public void doOnKill(int killCount, DefaultArenaPlayer arenaPlayer){
        arenaPlayer.minecraftPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 1,
                false, false));
        arenaPlayer.minecraftPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 6));
    }

    @Override
    public void putOnArmor(Player player) {
        if (defaultArmored instanceof IArmored.EmptyArmored)
            defaultArmored = new DefaultArmored(new ArmorSet(new UnbreakableItemStack(Material.IRON_HELMET),
                    new UnbreakableItemStack(Material.IRON_CHESTPLATE), new UnbreakableItemStack(Material.IRON_LEGGINGS),
                    new UnbreakableItemStack(Material.IRON_BOOTS)));
        defaultArmored.putOnArmor(player);
    }
}
