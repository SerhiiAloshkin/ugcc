package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToMediaEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToMediaEventHandler extends EventHandler {

    void onMedia(GoToMediaEvent event);
}
