package ua.coral.ugcc.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

import ua.coral.ugcc.admin.client.presenter.UpdateNewsPresenter;
import ua.coral.ugcc.common.client.DefaultAppController;
import ua.coral.ugcc.common.client.HistoryToken;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.user.client.event.ListNewsEvent;
import ua.coral.ugcc.user.client.event.OpenedNewsEvent;
import ua.coral.ugcc.user.client.event.handler.ListNewsEventHandler;
import ua.coral.ugcc.user.client.event.handler.OpenedNewsEventHandler;
import ua.coral.ugcc.user.client.presenter.ListNewsPresenter;
import ua.coral.ugcc.user.client.presenter.OpenedNewsPresenter;

public class UgccController extends DefaultAppController {

    private final UgccServiceAsync rpcService;
    private final HandlerManager eventBus;

    public UgccController(final UgccServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
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
        eventBus.addHandler(OpenedNewsEvent.TYPE, new OpenedNewsEventHandler() {
            @Override
            public void onOpenedNews(final OpenedNewsEvent event) {
                doOpenedNews(event.getNewsId());
            }
        });
    }

    private void doListNews() {
        History.newItem(HistoryToken.TO_NEWS.getToken());
    }

    private void doOpenedNews(final Long newsId) {
        History.newItem(HistoryToken.TO_OPENED_NEWS.getToken());
        final Presenter presenter = new OpenedNewsPresenter(rpcService, eventBus, newsId);
        presenter.go(getContainer());
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        super.onValueChange(event);

        final String token = event.getValue();

        if (token == null) {
            return;
        }

        if (HistoryToken.TO_NEWS.getToken().equals(token)) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(final Throwable reason) {

                }

                @Override
                public void onSuccess() {
                    new ListNewsPresenter(rpcService, eventBus).go(getContainer());
                }
            });
        }
    }
}
