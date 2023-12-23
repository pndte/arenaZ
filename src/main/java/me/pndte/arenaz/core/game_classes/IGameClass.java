package me.pndte.arenaz.core.game_classes;

import org.bukkit.entity.Player;

public interface IGameClass {
    public void applyClass(Player player);
    public class EmptyGameClass implements IGameClass{
        @Override
        public void applyClass(Player player) {
            player.getInventory().clear();
        }
    }
}
