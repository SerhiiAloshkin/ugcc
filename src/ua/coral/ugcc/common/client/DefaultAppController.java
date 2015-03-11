package ua.coral.ugcc.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.event.GoToContactsEvent;
import ua.coral.ugcc.common.event.GoToMainEvent;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.event.GoToNewsEvent;
import ua.coral.ugcc.common.event.GoToParishEvent;
import ua.coral.ugcc.common.event.GoToScheduleEvent;
import ua.coral.ugcc.common.event.handler.impl.GoToContactsEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToMainEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToMapEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToNewsEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToParishEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToScheduleEventHandlerImpl;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.MainPresenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

public class DefaultAppController implements Presenter, ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private HasWidgets container;

    public DefaultAppController(final HandlerManager eventBus) {
        this.eventBus = eventBus;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(GoToMainEvent.TYPE, new GoToMainEventHandlerImpl(eventBus, container));
        eventBus.addHandler(GoToNewsEvent.TYPE, new GoToNewsEventHandlerImpl());
        eventBus.addHandler(GoToParishEvent.TYPE, new GoToParishEventHandlerImpl());
        eventBus.addHandler(GoToScheduleEvent.TYPE, new GoToScheduleEventHandlerImpl());
        eventBus.addHandler(GoToContactsEvent.TYPE, new GoToContactsEventHandlerImpl());
        eventBus.addHandler(GoToMapEvent.TYPE, new GoToMapEventHandlerImpl());
    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem(HistoryToken.TO_MAIN.getToken());
        } else {
            History.fireCurrentHistoryState();
        }
    }

    protected HasWidgets getContainer() {
        return container;
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        final String token = event.getValue();

        if (token == null) {
            return;
        }

        if (HistoryToken.TO_MAIN.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new MainPresenter(eventBus, new DefaultBinder()).go(getContainer());
                }
            });
        }
    }
}
