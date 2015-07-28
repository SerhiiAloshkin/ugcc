package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.AddContactEvent;

import com.google.gwt.event.shared.EventHandler;

public interface AddContactEventHandler extends EventHandler {

    void onAddContact(AddContactEvent event);
}
