package me.pndte.arenaz.core.game_classes;

import me.pndte.arenaz.core.events.IEventTarget;
import me.pndte.arenaz.core.game_classes.absctractions.IArmored;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.other.UnbreakableItemStack;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class DefaultArenaClass implements IGameClass, IArmored {
    protected IArmored defaultArmored = new IArmored.EmptyArmored();
    public IEventTarget<Integer> onKill;
    public IEventTarget<Integer> onDeath;

    protected void doOnKill(int KillCount, DefaultArenaPlayer arenaPlayer){
        arenaPlayer.minecraftPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 1,
                false, false));
        arenaPlayer.minecraftPlayer().getInventory().addItem(new UnbreakableItemStack(Material.COOKED_BEEF, 4));
    }
}
