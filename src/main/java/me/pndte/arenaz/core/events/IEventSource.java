package me.pndte.arenaz.core.events;

public interface IEventSource<T> {
    public void add(IEventTarget<T> target);
    public void remove(IEventTarget<T> target);
    public void send(T args);
}
