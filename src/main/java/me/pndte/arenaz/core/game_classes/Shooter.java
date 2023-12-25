package me.pndte.arenaz.core.game_classes;

import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.other.UnbreakableItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Shooter implements IGameClass {
    @Override
    public void applyClass(Player player) {
        var crossbow = new UnbreakableItemStack(Material.CROSSBOW);
        crossbow.addEnchantment(Enchantment.QUICK_CHARGE, 2);
        crossbow.addEnchantment(Enchantment.MULTISHOT, 1);

        player.getInventory().setBoots(new UnbreakableItemStack(Material.LEATHER_BOOTS));
        player.getInventory().setLeggings(new UnbreakableItemStack(Material.LEATHER_LEGGINGS));
        player.getInventory().setChestplate(new UnbreakableItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setHelmet(new UnbreakableItemStack(Material.LEATHER_HELMET));

        player.getInventory().setItem(0, crossbow);
        player.getInventory().setItem(1, new UnbreakableItemStack(Material.COOKED_BEEF, 10));
        player.getInventory().setItem(2, new UnbreakableItemStack(Material.ARROW, 24));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1,  false, false));

    }
}
