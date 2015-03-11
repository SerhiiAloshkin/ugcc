package ua.coral.ugcc.common.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.common.event.handler.GoToNewsEventHandler;

public class GoToNewsEvent extends GwtEvent<GoToNewsEventHandler> {

    public static Type<GoToNewsEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToNewsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToNewsEventHandler handler) {
        handler.onNews(this);
    }
}
