package ua.coral.ugcc.common.event;

import ua.coral.ugcc.common.event.handler.GoToMapEventHandler;

import com.google.gwt.event.shared.GwtEvent;

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
