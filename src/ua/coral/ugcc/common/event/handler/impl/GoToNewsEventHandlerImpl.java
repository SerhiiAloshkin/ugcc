package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToNewsEvent;
import ua.coral.ugcc.common.event.handler.GoToNewsEventHandler;

import com.google.gwt.user.client.History;

public class GoToNewsEventHandlerImpl implements GoToNewsEventHandler {

    @Override
    public void onNews(final GoToNewsEvent event) {
        History.newItem(HistoryToken.TO_NEWS.getToken());
    }
}
