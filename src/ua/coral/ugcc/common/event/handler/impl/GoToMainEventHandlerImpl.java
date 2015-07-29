package ua.coral.ugcc.common.event.handler.impl;

import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.event.GoToMainEvent;
import ua.coral.ugcc.common.event.handler.GoToMainEventHandler;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.MainPresenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class GoToMainEventHandlerImpl implements GoToMainEventHandler {

    private final HandlerManager eventBus;
    private final HasWidgets container;

    public GoToMainEventHandlerImpl(final HandlerManager eventBus,
                                    final HasWidgets container) {
        this.eventBus = eventBus;
        this.container = container;
    }

    @Override
    public void onMain(final GoToMainEvent event) {
        History.newItem(HistoryToken.TO_MAIN.getToken(), false);
        final Presenter presenter = new MainPresenter(eventBus);
        presenter.go(container);
    }
}
