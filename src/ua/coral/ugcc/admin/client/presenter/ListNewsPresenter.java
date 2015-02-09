package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.callback.ListNewsCallback;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.place.shared.Place;
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
    }

    @Override
    public void goTo(final Place place) {

    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
        fetchListNews();
    }

    private void fetchListNews() {
        rpcService.listNews(new ListNewsCallback(view));
    }

    @Override
    public void countNews() {

    }

    @Override
    public void addNews(final News news) {

    }

    @Override
    public void removeNews(final News news) {

    }

    @Override
    public void updateNews(final News news) {

    }

    @Override
    public void listNews() {

    }
}
