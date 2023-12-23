package me.pndte.arenaz.core.action_blocks;

import me.pndte.arenaz.core.game_classes.IGameClass;
import me.pndte.arenaz.core.players.DefaultArenaPlayer;
import org.bukkit.block.Block;

public class GameClassifiableActionPlate implements IActionPlate {

    private final Block _plate;
    private IGameClass _gameClass;
    public GameClassifiableActionPlate(Block plate, IGameClass gameClass){
        _plate = plate;
        _gameClass = gameClass;
    }
    public GameClassifiableActionPlate(Block plate){
        this(plate, new IGameClass.EmptyGameClass());
    }

    @Override
    public void executeAction(DefaultArenaPlayer player) {
        player.setGameClass(_gameClass);
    }

    @Override
    public Block plate() {
        return _plate;
    }
}
