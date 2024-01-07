package me.pndte.arenaz.core.commands.actionPlates;

import me.pndte.arenaz.core.ArenaInfo;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommand implements CommandExecutor {
    protected final String commandName;
    public AbstractCommand(JavaPlugin plugin, String commandName){
        this.commandName = commandName;
        plugin.getCommand(commandName).setExecutor(this);
    }
}
