package ua.coral.ugcc.common.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.common.event.handler.GoToDirectionEventHandler;

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
