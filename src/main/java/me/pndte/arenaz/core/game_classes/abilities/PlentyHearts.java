package me.pndte.arenaz.core.game_classes.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlentyHearts implements ISpecialAbility{
    private Player _player;

    public PlentyHearts(Player player) {
        _player = player;
    }

    @Override
    public void run() {
        _player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 30, 4,false, false));
        _player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2, false, false));
    }
}
