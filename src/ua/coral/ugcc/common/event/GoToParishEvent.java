package ua.coral.ugcc.common.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.common.event.handler.GoToParishEventHandler;

public class GoToParishEvent extends GwtEvent<GoToParishEventHandler> {

    public static Type<GoToParishEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToParishEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToParishEventHandler handler) {
        handler.onParish(this);
    }
}
