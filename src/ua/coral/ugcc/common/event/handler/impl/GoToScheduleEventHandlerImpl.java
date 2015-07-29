package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToScheduleEvent;
import ua.coral.ugcc.common.event.handler.GoToScheduleEventHandler;

import com.google.gwt.user.client.History;

public class GoToScheduleEventHandlerImpl implements GoToScheduleEventHandler {

    @Override
    public void onSchedule(final GoToScheduleEvent event) {
        History.newItem(HistoryToken.TO_SCHEDULE.getToken());
    }
}
