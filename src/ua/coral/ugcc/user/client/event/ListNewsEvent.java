package ua.coral.ugcc.user.client.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.user.client.event.handler.ListNewsEventHandler;

public class ListNewsEvent extends GwtEvent<ListNewsEventHandler> {

    public static Type<ListNewsEventHandler> TYPE = new Type<>();

    @Override
    public Type<ListNewsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final ListNewsEventHandler handler) {
        handler.onListNews(this);
    }
}
