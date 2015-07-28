package ua.coral.ugcc.common.event.handler.impl;

import com.google.gwt.user.client.History;
import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToContactsEvent;
import ua.coral.ugcc.common.event.handler.GoToContactsEventHandler;

public class GoToContactsEventHandlerImpl implements GoToContactsEventHandler {

    @Override
    public void onContacts(final GoToContactsEvent event) {
        History.newItem(HistoryToken.TO_CONTACTS.getToken());
    }
}
