package me.pndte.arenaz.core.players.statistics;

import me.pndte.arenaz.core.players.statistics.statistic_element.StatisticsElement;

public class PlayerStatistics {
    public StatisticsElement<Integer> killCount;
    public StatisticsElement<Integer> deathCount;
    private int _totalWinCount;

    public PlayerStatistics(int totalKillCount, int totalDeathCount){
        killCount = new StatisticsElement<>(0, totalKillCount, (match, total) -> {
           killCount.set(match + 1, total + 1);
        });
        deathCount = new StatisticsElement<>(0, totalDeathCount, ((matchValue, totalValue) -> {
            deathCount.set(matchValue + 1, totalValue + 1);
        }));
    }
}
