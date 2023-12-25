package me.pndte.arenaz.core.players.statistics;

import java.util.concurrent.Callable;

public class StatisticsElement<T> {
    private T _matchValue;
    private final T _startValue;
    private T _totalValue;
    private final IIncreaseStatisticsElement<T> _increaseMethod;

    public StatisticsElement(T matchNullValue, T totalValue, IIncreaseStatisticsElement<T> increaseMethod) {
        _matchValue = matchNullValue;
        _startValue = matchNullValue;
        _totalValue = totalValue;
        _increaseMethod = increaseMethod;
    }

    public void Increase() {
        _increaseMethod.increase(_matchValue, _totalValue);
    }

    public void ClearMatchValue() {
        _matchValue = _startValue;
    }

    public T matchValue() {
        return _matchValue;
    }

    public T totalValue() {
        return _totalValue;
    }
}
