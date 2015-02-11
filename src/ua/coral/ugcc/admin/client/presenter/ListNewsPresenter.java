package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.AdminModeServiceDelegate;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.HasWidgets;

public class ListNewsPresenter implements Presenter, ListNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final ListNewsView view;
    private AdminModeServiceDelegate delegate;

    public ListNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                             final ListNewsView view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.view = view;
        delegate = new AdminModeServiceDelegate(rpcService, view);

        view.setPresenter(this);
        view.init();
    }

    @Override
    public void goTo(final Place place) {

    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void countNews() {
        delegate.countNews();
    }

    @Override
    public void addNews(final News news) {
        delegate.addNews(news);
    }

    @Override
    public void removeNews(final News news) {
        delegate.removeNews(news);
    }

    @Override
    public void updateNews(final News news) {
        delegate.updateNews(news);
    }

    @Override
    public void listNews() {
        delegate.listNews();
    }
}
