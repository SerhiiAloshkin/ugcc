package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToMainEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToMainEventHandler extends EventHandler {

    void onMain(GoToMainEvent event);
}
