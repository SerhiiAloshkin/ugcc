package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToDirectionEvent;
import ua.coral.ugcc.common.event.handler.GoToDirectionEventHandler;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DirectionPresenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class GoToDirectionEventHandlerImpl implements GoToDirectionEventHandler {

    private final HandlerManager eventBus;
    private final HasWidgets container;

    public GoToDirectionEventHandlerImpl(final HandlerManager eventBus, final HasWidgets container) {
        this.eventBus = eventBus;
        this.container = container;
    }

    @Override
    public void onDirection(final GoToDirectionEvent event) {
        History.newItem(HistoryToken.TO_DIRECTION.getToken(), false);
        final Presenter presenter = new DirectionPresenter(eventBus, new DefaultBinder());
        presenter.go(container);
    }
}
