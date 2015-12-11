package ua.coral.ugcc.common.event;

import ua.coral.ugcc.common.event.handler.GoToMediaEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class GoToMediaEvent extends GwtEvent<GoToMediaEventHandler> {

    public static Type<GoToMediaEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToMediaEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToMediaEventHandler handler) {
        handler.onMedia(this);
    }
}
