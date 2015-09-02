package ua.coral.ugcc.user.client.event.handler;

import ua.coral.ugcc.user.client.event.ListContactsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ListContactsEventHandler extends EventHandler {

    void onListContacts(ListContactsEvent event);
}
