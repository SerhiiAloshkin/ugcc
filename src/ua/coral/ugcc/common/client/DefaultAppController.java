package ua.coral.ugcc.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.event.GoToContactsEvent;
import ua.coral.ugcc.common.event.GoToDirectionEvent;
import ua.coral.ugcc.common.event.GoToMainEvent;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.event.GoToNewsEvent;
import ua.coral.ugcc.common.event.GoToParishEvent;
import ua.coral.ugcc.common.event.GoToScheduleEvent;
import ua.coral.ugcc.common.event.handler.impl.GoToContactsEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToDirectionEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToMainEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToMapEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToNewsEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToParishEventHandlerImpl;
import ua.coral.ugcc.common.event.handler.impl.GoToScheduleEventHandlerImpl;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DirectionPresenter;
import ua.coral.ugcc.common.presenter.impl.MainPresenter;
import ua.coral.ugcc.common.presenter.impl.MapPresenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

public class DefaultAppController implements Presenter, ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private HasWidgets container;

    public DefaultAppController(final HandlerManager eventBus) {
        this.eventBus = eventBus;
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(GoToMainEvent.TYPE, new GoToMainEventHandlerImpl(eventBus, container));
        eventBus.addHandler(GoToNewsEvent.TYPE, new GoToNewsEventHandlerImpl());
        eventBus.addHandler(GoToParishEvent.TYPE, new GoToParishEventHandlerImpl());
        eventBus.addHandler(GoToScheduleEvent.TYPE, new GoToScheduleEventHandlerImpl());
        eventBus.addHandler(GoToContactsEvent.TYPE, new GoToContactsEventHandlerImpl());
        eventBus.addHandler(GoToMapEvent.TYPE, new GoToMapEventHandlerImpl(eventBus, container));
        eventBus.addHandler(GoToDirectionEvent.TYPE, new GoToDirectionEventHandlerImpl(eventBus, container));
    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;

        bind();

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
        } else if (HistoryToken.TO_MAP.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new MapPresenter(eventBus, new DefaultBinder()).go(getContainer());
                }
            });
        } else if (HistoryToken.TO_DIRECTION.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new DirectionPresenter(eventBus, new DefaultBinder()).go(getContainer());
                }
            });
        }
    }
}
