package ua.coral.ugcc.common.event;

import ua.coral.ugcc.common.event.handler.GoToDirectionEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class GoToDirectionEvent extends GwtEvent<GoToDirectionEventHandler> {

    public static Type<GoToDirectionEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToDirectionEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToDirectionEventHandler handler) {
        handler.onDirection(this);
    }
}
