package me.pndte.arenaz.core.game_classes;

import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.other.UnbreakableItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Barbarian implements IGameClass {
    @Override
    public void applyClass(Player player) {
        player.getInventory().setBoots(new UnbreakableItemStack(Material.IRON_BOOTS));
        player.getInventory().setLeggings(new UnbreakableItemStack(Material.CHAINMAIL_LEGGINGS));
        player.getInventory().setChestplate(new UnbreakableItemStack(Material.CHAINMAIL_CHESTPLATE));
        player.getInventory().setHelmet(new UnbreakableItemStack(Material.CHAINMAIL_HELMET));
        player.getInventory().setItem(0, new UnbreakableItemStack(Material.IRON_AXE));
        player.getInventory().setItem(1, new UnbreakableItemStack(Material.COOKED_BEEF, 8));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1,  false, false));
    }
}
