package ua.coral.ugcc.common.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.common.event.GoToScheduleEvent;

public interface GoToScheduleEventHandler extends EventHandler {

    void onSchedule(GoToScheduleEvent event);
}
