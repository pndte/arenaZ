package me.pndte.arenaz.core.events;

public class DefaultEventTarget<T> implements IEventTarget<T>{
    private final IEventAction<T> _action;
    public DefaultEventTarget(IEventAction<T> action){
        this._action = action;
    }
    @Override
    public void receive(T args) {
        _action.run(args);
    }
}


