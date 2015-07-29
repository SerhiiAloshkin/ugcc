package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToDirectionEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToDirectionEventHandler extends EventHandler {

    void onDirection(GoToDirectionEvent event);
}
