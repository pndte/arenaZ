package me.pndte.arenaz.core.game_classes;

import me.pndte.arenaz.core.game_classes.absctractions.IArmored;
import me.pndte.arenaz.core.other.ArmorSet;
import org.bukkit.entity.Player;

public class DefaultArmored implements IArmored {
    private final ArmorSet _armor;
    public DefaultArmored(ArmorSet armor){
        _armor = armor;
    }
    @Override
    public void putOnArmor(Player player) {
        player.getInventory().setHelmet(_armor.helmet());
        player.getInventory().setChestplate(_armor.chestPlate());
        player.getInventory().setLeggings(_armor.leggings());
        player.getInventory().setBoots(_armor.boots());
    }
}