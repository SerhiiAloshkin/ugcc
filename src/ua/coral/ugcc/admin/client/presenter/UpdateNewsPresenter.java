package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.view.UpdateNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class UpdateNewsPresenter implements Presenter, UpdateNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final UpdateNewsView view;

    public UpdateNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                               final UpdateNewsView view, final Long newsId) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.view = view;

        view.setPresenter(this);
        view.init();

        loadNewsById(newsId);
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    private void loadNewsById(final Long newsId) {
        rpcService.getNewsById(newsId, new AsyncCallback<News>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final News news) {
                view.setNews(news);
            }
        });
    }

    @Override
    public void updateNews(final News news) {
        rpcService.updateNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final Void result) {
                eventBus.fireEvent(new ListNewsEvent());
            }
        });
    }

    @Override
    public void listNews() {
        eventBus.fireEvent(new ListNewsEvent());
    }
}
