package ua.coral.ugcc.common.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.common.event.handler.GoToMapEventHandler;

public class GoToMapEvent extends GwtEvent<GoToMapEventHandler> {

    public static Type<GoToMapEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToMapEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToMapEventHandler handler) {
        handler.onMap(this);
    }
}
