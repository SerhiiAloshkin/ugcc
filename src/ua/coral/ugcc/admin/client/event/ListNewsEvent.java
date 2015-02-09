package ua.coral.ugcc.admin.client.event;

import ua.coral.ugcc.admin.client.event.handler.ListNewsEventHandler;

import com.google.gwt.event.shared.GwtEvent;

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
