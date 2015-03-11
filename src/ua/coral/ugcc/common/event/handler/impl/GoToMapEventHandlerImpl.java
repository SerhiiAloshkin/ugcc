package ua.coral.ugcc.common.event.handler.impl;

import com.google.gwt.user.client.History;
import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.event.handler.GoToMapEventHandler;

public class GoToMapEventHandlerImpl implements GoToMapEventHandler {

    @Override
    public void onMap(final GoToMapEvent event) {
        History.newItem(HistoryToken.TO_MAP.getToken());
    }
}
