package ua.coral.ugcc.common.event.handler.impl;

import com.google.gwt.user.client.History;
import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToParishEvent;
import ua.coral.ugcc.common.event.handler.GoToParishEventHandler;

public class GoToParishEventHandlerImpl implements GoToParishEventHandler {

    @Override
    public void onParish(final GoToParishEvent event) {
        History.newItem(HistoryToken.TO_PARISH.getToken());
    }
}
