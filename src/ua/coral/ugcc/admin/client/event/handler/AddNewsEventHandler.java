package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.AddNewsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface AddNewsEventHandler extends EventHandler {

    void onAddNews(AddNewsEvent event);
}
