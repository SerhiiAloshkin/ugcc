package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.event.handler.GoToMapEventHandler;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.MapPresenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class GoToMapEventHandlerImpl implements GoToMapEventHandler {

    private final HandlerManager eventBus;
    private final HasWidgets container;

    public GoToMapEventHandlerImpl(final HandlerManager eventBus, final HasWidgets container) {
        this.eventBus = eventBus;
        this.container = container;
    }

    @Override
    public void onMap(final GoToMapEvent event) {
        History.newItem(HistoryToken.TO_MAP.getToken(), false);
        final Presenter presenter = new MapPresenter(eventBus, new DefaultBinder());
        presenter.go(container);
    }
}
