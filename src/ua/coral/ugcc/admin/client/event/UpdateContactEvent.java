package ua.coral.ugcc.admin.client.event;

import ua.coral.ugcc.admin.client.event.handler.UpdateContactEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateContactEvent extends GwtEvent<UpdateContactEventHandler> {

    public static Type<UpdateContactEventHandler> TYPE = new Type<>();

    private final Long contactId;

    public UpdateContactEvent(final Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }

    @Override
    public Type<UpdateContactEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final UpdateContactEventHandler handler) {
        handler.onUpdateContact(this);
    }
}
