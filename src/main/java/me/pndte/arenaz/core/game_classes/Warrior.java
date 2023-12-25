package me.pndte.arenaz.core.game_classes;

import me.pndte.arenaz.core.game_classes.absctractions.IDeathActionable;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.game_classes.absctractions.IKillActionable;
import me.pndte.arenaz.core.other.UnbreakableItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Warrior implements IGameClass, IKillActionable, IDeathActionable {
    @Override
    public void applyClass(Player player) {
        player.getInventory().setBoots(new UnbreakableItemStack(Material.IRON_BOOTS));
        player.getInventory().setLeggings(new UnbreakableItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setChestplate(new UnbreakableItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setHelmet(new UnbreakableItemStack(Material.IRON_HELMET));
        player.getInventory().setItem(0, new UnbreakableItemStack(Material.IRON_SWORD));
        player.getInventory().setItemInOffHand(new UnbreakableItemStack(Material.SHIELD));
        player.getInventory().setItem(1, new UnbreakableItemStack(Material.COOKED_BEEF, 12));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
    }

    @Override
    public void executeAfterKill(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
        player.getInventory().addItem(new UnbreakableItemStack(Material.COOKED_BEEF, 4));
    }

    @Override
    public void executeAfterDeath(Player player) {
    }
}
