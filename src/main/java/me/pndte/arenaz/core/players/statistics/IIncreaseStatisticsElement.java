package me.pndte.arenaz.core.players.statistics;

public interface IIncreaseStatisticsElement<T> {
    public void increase(T matchValue, T totalValue);
}
