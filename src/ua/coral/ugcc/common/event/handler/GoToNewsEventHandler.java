package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToNewsEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToNewsEventHandler extends EventHandler {

    void onNews(GoToNewsEvent event);
}
