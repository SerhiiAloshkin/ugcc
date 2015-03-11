package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToMapEvent;

public interface GoToMapEventHandler extends EventHandler {

    void onMap(GoToMapEvent event);
}
