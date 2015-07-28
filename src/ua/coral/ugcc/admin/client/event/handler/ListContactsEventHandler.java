package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.ListContactsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ListContactsEventHandler extends EventHandler {

    void onListContacts(ListContactsEvent event);
}
