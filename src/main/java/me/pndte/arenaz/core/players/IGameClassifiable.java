package me.pndte.arenaz.core.players;

import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;

public interface IGameClassifiable {
    public IGameClass gameClass();
    public void setGameClass(IGameClass gameClass);
}
