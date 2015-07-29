package ua.coral.ugcc.common.event;

import ua.coral.ugcc.common.event.handler.GoToContactsEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class GoToContactsEvent extends GwtEvent<GoToContactsEventHandler> {

    public static Type<GoToContactsEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToContactsEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToContactsEventHandler handler) {
        handler.onContacts(this);
    }
}
