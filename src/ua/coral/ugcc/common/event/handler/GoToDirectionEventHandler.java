package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToDirectionEvent;

public interface GoToDirectionEventHandler extends EventHandler {

    void onDirection(GoToDirectionEvent event);
}
