package me.pndte.arenaz.core.players;

import me.pndte.arenaz.core.game_classes.IGameClass;
import org.bukkit.entity.Player;

public class DefaultArenaPlayer implements IArenaPlayer, IGameClassifiable {
    private final Player _player;
    private IGameClass _gameClass;
    public DefaultArenaPlayer(Player player, IGameClass gameClass){
        _player = player;
        _gameClass = gameClass;
    }

    public DefaultArenaPlayer(Player player) {
        this(player, new IGameClass.EmptyGameClass());
    }

    @Override
    public Player minecraftPlayer() { return _player; }


    @Override
    public IGameClass gameClass() { return _gameClass; }

    @Override
    public void setGameClass(IGameClass gameClass) {
        _gameClass = gameClass;
         _player.getInventory().clear();
        _gameClass.applyClass(_player);
    }
}
