package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToMapEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToMapEventHandler extends EventHandler {

    void onMap(GoToMapEvent event);
}
