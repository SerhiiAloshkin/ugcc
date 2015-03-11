package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToMainEvent;

public interface GoToMainEventHandler extends EventHandler {

    void onMain(GoToMainEvent event);
}
