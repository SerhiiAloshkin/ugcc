package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.ListNewsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ListNewsEventHandler extends EventHandler {

    void onListNews(ListNewsEvent event);
}
