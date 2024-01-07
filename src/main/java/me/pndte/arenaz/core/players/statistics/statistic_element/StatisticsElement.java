package me.pndte.arenaz.core.players.statistics.statistic_element;

import me.pndte.arenaz.core.events.DefaultEventSource;
import me.pndte.arenaz.core.events.IEventSource;
import me.pndte.arenaz.core.players.statistics.IIncreaseStatisticsElement;

public class StatisticsElement<T> {
    private T _matchValue;
    public IEventSource<T> matchValueChanged;
    private T _totalValue;
    public IEventSource<T> totalValueChanged;
    private final IIncreaseStatisticsElement<T> _increaseMethod;

    public StatisticsElement(T startValue, T totalValue, IIncreaseStatisticsElement<T> increaseMethod) {
        _matchValue = startValue;
        _totalValue = totalValue;
        _increaseMethod = increaseMethod;
        matchValueChanged = new DefaultEventSource<>();
        totalValueChanged = new DefaultEventSource<>();
    }

    public void Increase() {
        _increaseMethod.increase(_matchValue, _totalValue);
//        matchValueChanged.send(_matchValue);
//        totalValueChanged.send(_totalValue);
    }

    public void set(T matchValue, T totalValue){ //TODO: передалть под Increase
        _matchValue = matchValue;
        _totalValue = totalValue;
        matchValueChanged.send(_matchValue);
        totalValueChanged.send(_totalValue);

    }

    public void ClearMatchValue(T valueTo) {
        _matchValue = valueTo;
    }

    public T matchValue() {
        return _matchValue;
    }

    public T totalValue() {
        return _totalValue;
    }
}
