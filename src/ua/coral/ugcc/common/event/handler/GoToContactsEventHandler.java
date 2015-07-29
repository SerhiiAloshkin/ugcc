package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToContactsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToContactsEventHandler extends EventHandler {

    void onContacts(GoToContactsEvent event);
}
