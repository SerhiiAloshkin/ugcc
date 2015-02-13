package ua.coral.ugcc.admin.client.event;

import ua.coral.ugcc.admin.client.event.handler.AddNewsEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class AddNewsEvent extends GwtEvent<AddNewsEventHandler> {

    public static Type<AddNewsEventHandler> TYPE = new Type<>();

    @Override
    public Type<AddNewsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final AddNewsEventHandler handler) {
        handler.onAddNews(this);
    }
}
