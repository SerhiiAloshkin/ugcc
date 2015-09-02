package ua.coral.ugcc.user.client.event;

import ua.coral.ugcc.user.client.event.handler.ListContactsEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ListContactsEvent extends GwtEvent<ListContactsEventHandler> {

    public static Type<ListContactsEventHandler> TYPE = new Type<>();

    @Override
    public Type<ListContactsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final ListContactsEventHandler handler) {
        handler.onListContacts(this);
    }
}
