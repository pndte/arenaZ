package me.pndte.arenaz.core.events;

import java.util.ArrayList;
import java.util.List;

public class DefaultEventSource<T> implements IEventSource<T> {
    private final List<IEventTarget<T>> _targets = new ArrayList<>();
    @Override
    public void add(IEventTarget<T> target) {
        _targets.add(target);
    }

    @Override
    public void remove(IEventTarget<T> target) {
        _targets.remove(target);
    }

    @Override
    public void send(T args) {
        for (var target : _targets){
            target.receive(args);
        }
    }
}
