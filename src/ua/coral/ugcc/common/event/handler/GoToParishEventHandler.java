package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToParishEvent;

public interface GoToParishEventHandler extends EventHandler {

    void onParish(GoToParishEvent event);
}
