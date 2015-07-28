package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.UpdateContactEvent;

import com.google.gwt.event.shared.EventHandler;

public interface UpdateContactEventHandler extends EventHandler {

    void onUpdateContact(UpdateContactEvent event);
}
