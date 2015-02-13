package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.AddNewsEvent;
import ua.coral.ugcc.admin.client.event.UpdateNewsEvent;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class ListNewsPresenter implements Presenter, ListNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final ListNewsView view;

    public ListNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                             final ListNewsView view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.view = view;

        view.setPresenter(this);
        view.init();
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void countNews() {
//        delegate.countNews();
    }

    @Override
    public void addNews() {
        eventBus.fireEvent(new AddNewsEvent());
    }

    @Override
    public void removeNews(final News news) {
        rpcService.removeNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final Void result) {

            }
        });
    }

    @Override
    public void updateNews(final News news) {
        eventBus.fireEvent(new UpdateNewsEvent(news.getId()));
    }

    @Override
    public void listNews() {
        rpcService.listNews(new AsyncCallback<List<News>>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final List<News> newsList) {
                view.loadNews(newsList);
            }
        });
    }
}
