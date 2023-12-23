package me.pndte.arenaz.core.players;

import org.bukkit.entity.Player;

public class DefaultArenaPlayer implements IArenaPlayer {
    private final Player _player;

    public DefaultArenaPlayer(Player player) {
        _player = player;
    }

    @Override
    public Player minecraftPlayer() {
        return _player;
    }
}
