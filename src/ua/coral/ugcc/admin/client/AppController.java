package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.event.AddNewsEvent;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.event.UpdateNewsEvent;
import ua.coral.ugcc.admin.client.event.handler.AddNewsEventHandler;
import ua.coral.ugcc.admin.client.event.handler.ListNewsEventHandler;
import ua.coral.ugcc.admin.client.event.handler.UpdateNewsEventHandler;
import ua.coral.ugcc.admin.client.presenter.AddNewsPresenter;
import ua.coral.ugcc.admin.client.presenter.ListNewsPresenter;
import ua.coral.ugcc.admin.client.presenter.Presenter;
import ua.coral.ugcc.admin.client.presenter.UpdateNewsPresenter;
import ua.coral.ugcc.admin.client.view.impl.AddNewsViewImpl;
import ua.coral.ugcc.admin.client.view.impl.ListNewsViewImpl;
import ua.coral.ugcc.admin.client.view.impl.UpdateNewsViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private HasWidgets container;

    public AppController(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(ListNewsEvent.TYPE, new ListNewsEventHandler() {
            @Override
            public void onListNews(final ListNewsEvent event) {
                doListNews();
            }
        });
        eventBus.addHandler(AddNewsEvent.TYPE, new AddNewsEventHandler() {
            @Override
            public void onAddNews(final AddNewsEvent event) {
                doAddNews();
            }
        });
        eventBus.addHandler(UpdateNewsEvent.TYPE, new UpdateNewsEventHandler() {
            @Override
            public void onUpdateNews(final UpdateNewsEvent event) {
                doUpdateNews(event.getNewsId());
            }
        });
    }

    private void doListNews() {
        History.newItem(HistoryToken.LIST_NEWS.getToken());
    }

    private void doAddNews() {
        History.newItem(HistoryToken.ADD_NEWS.getToken());
    }

    private void doUpdateNews(final Long newsId) {
        History.newItem(HistoryToken.UPDATE_NEWS.getToken(), false);
        final Presenter presenter = new UpdateNewsPresenter(rpcService, eventBus, new UpdateNewsViewImpl(), newsId);
        presenter.go(container);
    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem(HistoryToken.LIST_NEWS.getToken());
        } else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        final String token = event.getValue();

        if (token == null) {
            return;
        }

        if (HistoryToken.LIST_NEWS.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new ListNewsPresenter(rpcService, eventBus, new ListNewsViewImpl()).go(container);
                }
            });
        } else if (HistoryToken.ADD_NEWS.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new AddNewsPresenter(rpcService, eventBus, new AddNewsViewImpl()).go(container);
                }
            });
        }
    }
}
