package me.pndte.arenaz.core.commands.actionPlates;

import me.pndte.arenaz.core.ArenaInfo;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommand implements CommandExecutor {
    protected final String commandName;
    protected final ArenaInfo arenaInfo;
    public AbstractCommand(JavaPlugin plugin, String commandName, ArenaInfo arenaInfo){
        this.commandName = commandName;
        this.arenaInfo = arenaInfo;
        plugin.getCommand(commandName).setExecutor(this);
    }
}
