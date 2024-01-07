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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Barbarian extends DefaultArenaClass {
    @Override
    public void applyClass(Player player) {
        var arenaPlayer = ArenaInfo.getPlayer(player.getUniqueId());

        onKill = new DefaultEventTarget<>(args -> {
            doOnKill(args, arenaPlayer);
        });
        onDeath = new DefaultEventTarget<>(args -> {player.getInventory().clear();});

        arenaPlayer.playerStatistics().killCount.matchValueChanged.add(onKill);
        arenaPlayer.playerStatistics().deathCount.matchValueChanged.add(onDeath);

        putOnArmor(player);
        var axe = new UnbreakableItemStack(Material.DIAMOND_AXE);
        player.getInventory().setItem(0, axe);
        player.getInventory().setItemInOffHand(new ItemStack(Material.COOKED_BEEF, 8));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1,  false, false));
    }
    @Override
    public void doOnKill(int KillCount, DefaultArenaPlayer arenaPlayer){
        arenaPlayer.minecraftPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 1,
                false, false));
        arenaPlayer.minecraftPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 6));
    }

    @Override
    public void putOnArmor(Player player) {
        var chestPlate = new UnbreakableItemStack(Material.CHAINMAIL_CHESTPLATE);
        chestPlate.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        defaultArmored = new DefaultArmored(new ArmorSet(new UnbreakableItemStack(Material.CHAINMAIL_HELMET),
                chestPlate, new UnbreakableItemStack(Material.CHAINMAIL_LEGGINGS),
                new UnbreakableItemStack(Material.IRON_BOOTS)));
        defaultArmored.putOnArmor(player);
    }
}
