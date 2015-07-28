package ua.coral.ugcc.admin.client.event;

import ua.coral.ugcc.admin.client.event.handler.AddContactEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class AddContactEvent extends GwtEvent<AddContactEventHandler> {

    public static Type<AddContactEventHandler> TYPE = new Type<>();

    @Override
    public Type<AddContactEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final AddContactEventHandler handler) {
        handler.onAddContact(this);
    }
}
