package ua.coral.ugcc.common.event.handler;

import ua.coral.ugcc.common.event.GoToScheduleEvent;

import com.google.gwt.event.shared.EventHandler;

public interface GoToScheduleEventHandler extends EventHandler {

    void onSchedule(GoToScheduleEvent event);
}
