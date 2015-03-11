package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToNewsEvent;

public interface GoToNewsEventHandler extends EventHandler {

    void onNews(GoToNewsEvent event);
}
