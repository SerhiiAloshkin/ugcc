package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToMediaEvent;
import ua.coral.ugcc.common.event.handler.GoToMediaEventHandler;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.MapPresenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class GoToMediaEventHandlerImpl implements GoToMediaEventHandler {

    private final HandlerManager eventBus;
    private final HasWidgets container;

    public GoToMediaEventHandlerImpl(final HandlerManager eventBus, final HasWidgets container) {
        this.eventBus = eventBus;
        this.container = container;
    }

    @Override
    public void onMedia(final GoToMediaEvent event) {
        History.newItem(HistoryToken.TO_MEDIA.getToken(), false);
        final Presenter presenter = new MapPresenter(eventBus, new DefaultBinder());
        presenter.go(container);
    }
}
