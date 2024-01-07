package me.pndte.arenaz.core.game_classes.absctractions;

import me.pndte.arenaz.core.game_classes.old.Barbarian;
import me.pndte.arenaz.core.game_classes.EGameClass;
import me.pndte.arenaz.core.game_classes.old.Shooter;
import me.pndte.arenaz.core.game_classes.old.Warrior;
import org.bukkit.entity.Player;

public interface IGameClass {
    public void applyClass(Player player);
    public class EmptyGameClass implements IGameClass{
        @Override
        public void applyClass(Player player) {
            player.getInventory().clear();
        }
    }

    public static IGameClass classFromString(String string){
        EGameClass className = EGameClass.valueOf(string);
        switch (className){ //TODO: переделать
            case Shooter:
                return new Shooter();
            case Warrior:
                return new Warrior();
            case Barbarian:
                return new Barbarian();
        }
        return new EmptyGameClass();
    }
}
