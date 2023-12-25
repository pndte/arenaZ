package me.pndte.arenaz.core.players.statistics;

import org.bukkit.entity.Player;

public class PlayerStatistics {
    public StatisticsElement<Integer> killCount;
    public StatisticsElement<Integer> deathCount;
    private int _totalWinCount;

    public PlayerStatistics(int totalKillCount, int totalDeathCount){
        killCount = new StatisticsElement<>(0, totalKillCount, ((matchValue, totalValue) -> {
            matchValue++;
            totalValue++;
        }));
        deathCount = new StatisticsElement<>(0, totalDeathCount, ((matchValue, totalValue) -> {
            matchValue++;
            totalValue++;
        }));
    }
}
