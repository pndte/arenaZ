package me.pndte.arenaz.core.events;

public interface IEventAction<T> {
    public void run(T args);
}
