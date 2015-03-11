package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToContactsEvent;

public interface GoToContactsEventHandler extends EventHandler {

    void onContacts(GoToContactsEvent event);
}
