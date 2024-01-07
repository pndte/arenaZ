package me.pndte.arenaz.core.commands.actionPlates;

import me.pndte.arenaz.core.ArenaInfo;
import me.pndte.arenaz.core.action_blocks.GameClassifiableActionPlate;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetActionPlateCommand extends AbstractCommand {
    public SetActionPlateCommand(JavaPlugin plugin, String commandName) {
        super(plugin, commandName);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if(args.length != 1) return false;
        var player = (Player) commandSender;
        var block = player.getTargetBlock(null, 10);
        if(block.getType() != Material.ACACIA_PRESSURE_PLATE && //TODO: переделать
        block.getType() != Material.BAMBOO_PRESSURE_PLATE &&        
        block.getType() != Material.POLISHED_BLACKSTONE_PRESSURE_PLATE &&
        block.getType() != Material.BIRCH_PRESSURE_PLATE &&
        block.getType() != Material.CHERRY_PRESSURE_PLATE &&
        block.getType() != Material.CRIMSON_PRESSURE_PLATE &&
        block.getType() != Material.DARK_OAK_PRESSURE_PLATE &&
        block.getType() != Material.JUNGLE_PRESSURE_PLATE&&
        block.getType() != Material.MANGROVE_PRESSURE_PLATE&&
        block.getType() != Material.OAK_PRESSURE_PLATE&&
        block.getType() != Material.SPRUCE_PRESSURE_PLATE&&
        block.getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE&&
        block.getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE&&
        block.getType() != Material.STONE_PRESSURE_PLATE &&
        block.getType() != Material.WARPED_PRESSURE_PLATE){
            return false;
        }
        //if (!Arrays.stream(EGameClass.values()).toList().contains(args[0])) {return false;} TODO: вернуть + починить
        IGameClass gameClass = IGameClass.classFromString(args[0]);

        ArenaInfo.addActionPlate(new GameClassifiableActionPlate(block, gameClass));
        return true;
    }
}





