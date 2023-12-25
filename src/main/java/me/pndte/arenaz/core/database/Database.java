package me.pndte.arenaz.core.database;

import me.pndte.arenaz.core.action_blocks.GameClassifiableActionPlate;
import me.pndte.arenaz.core.action_blocks.IActionPlate;
import me.pndte.arenaz.core.game_classes.absctractions.IGameClass;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Database {
    private final String _plateTableName;

    public Database(String plateTableName) {
        this._plateTableName = plateTableName;
        try {
            Files.createDirectories(Paths.get("plugins/arenaZData/"));
            var connection = DriverManager.getConnection("jdbc:sqlite:plugins/arenaZData/.db");
            var statement = connection.createStatement();
            statement.executeUpdate("create table if not exists \'" + plateTableName + "\'" +
                    " (\'id\' INTEGER PRIMARY KEY AUTOINCREMENT, \'x\' INTEGER, \'y\' INTEGER," +
                    "\'z\' INTEGER, \'gameClass\' TEXT)");
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void WriteActionPlates(Collection<IActionPlate> actionPlateHashMap) {
        for (var actionPlate : actionPlateHashMap) {
            var plateLocation = actionPlate.plate().getLocation();
            try {
                var connection = DriverManager.getConnection("jdbc:sqlite:plugins/arenaZData/.db");
                var statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO " + _plateTableName + "(x, y, z, gameClass) VALUES (\'" +
                        plateLocation.getBlockX() + "\', \'" + plateLocation.getBlockY()
                        + "\', \'" + plateLocation.getBlockZ() + "\', \'" +
                        actionPlate.gameClass().getClass().getSimpleName() + "\');");
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Collection<IActionPlate> readActionPlates(World world){
        try {
            var connection = DriverManager.getConnection("jdbc:sqlite:plugins/arenaZData/.db");
            var statement = connection.createStatement();
            var result = statement.executeQuery("SELECT * FROM " + _plateTableName);
            List<IActionPlate> plates = new ArrayList<>();
            if (!result.isBeforeFirst()) return plates;
            do {
                var plateLocation = new Location(world, result.getInt("x"), result.getInt("y"),
                        result.getInt("z"));
                var gameClass = IGameClass.classFromString(result.getString("gameClass"));
                plates.add(new GameClassifiableActionPlate(plateLocation.getBlock(), gameClass));
            } while (result.next());

            connection.close();
            return plates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearPlatesTable(){
        try {
            var connection = DriverManager.getConnection("jdbc:sqlite:plugins/arenaZData/.db");
            var statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + _plateTableName);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
