package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.event.handler.ListNewsEventHandler;
import ua.coral.ugcc.admin.client.presenter.ListNewsPresenter;
import ua.coral.ugcc.admin.client.presenter.Presenter;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.admin.client.view.impl.ListNewsViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

    private static final String LIST_NEWS = "listNews";
    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private HasWidgets container;
    private ListNewsView listNewsView;

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
    }

    private void doListNews() {
        History.newItem(LIST_NEWS, false);
    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem(LIST_NEWS);
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

        if (LIST_NEWS.equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    if (listNewsView == null) {
                        listNewsView = new ListNewsViewImpl();
                    }

                    new ListNewsPresenter(rpcService, eventBus, listNewsView).go(container);
                }
            });
        }
    }
}
