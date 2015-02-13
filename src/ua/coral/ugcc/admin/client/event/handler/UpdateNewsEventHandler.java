package ua.coral.ugcc.admin.client.event.handler;

import ua.coral.ugcc.admin.client.event.UpdateNewsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface UpdateNewsEventHandler extends EventHandler {

    void onUpdateNews(UpdateNewsEvent event);
}
