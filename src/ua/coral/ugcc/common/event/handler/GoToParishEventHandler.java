package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToParishEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToParishEventHandler extends EventHandler {

    void onParish(GoToParishEvent event);
}
