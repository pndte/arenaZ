package me.pndte.arenaz.core.players;

import me.pndte.arenaz.core.events.IEventTarget;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import me.pndte.arenaz.core.game_classes.absctractions.IStatistical;
import me.pndte.arenaz.core.players.statistics.PlayerStatistics;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.List;

public class DefaultArenaPlayer implements IArenaPlayer, IGameClassifiable, IStatistical {
    private final Player _player;
    private IGameClass _gameClass;
    private PlayerStatistics _playerStatistics = new PlayerStatistics(0,0);
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
        for(var effect: _player.getActivePotionEffects()) {
            _player.removePotionEffect(effect.getType());
        }
        _player.setGameMode(GameMode.ADVENTURE);
        _gameClass = gameClass;
         _player.getInventory().clear();
        _gameClass.applyClass(_player);
    }

    @Override
    public PlayerStatistics playerStatistics() {
        return _playerStatistics;
    }
}
