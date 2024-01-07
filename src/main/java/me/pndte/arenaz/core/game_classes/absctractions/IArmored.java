package me.pndte.arenaz.core.game_classes.absctractions;

import org.bukkit.entity.Player;

public interface IArmored {
    public void putOnArmor(Player player);

    public class EmptyArmored implements IArmored{
        @Override
        public void putOnArmor(Player player) {}
    }

}
