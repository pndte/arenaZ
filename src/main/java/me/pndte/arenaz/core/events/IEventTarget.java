package me.pndte.arenaz.core.events;

public interface IEventTarget<T> {
    public void receive(T args);
}

