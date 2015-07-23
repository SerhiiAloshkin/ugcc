package ua.coral.ugcc.user.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.user.client.UgccServiceAsync;
import ua.coral.ugcc.user.client.event.OpenedNewsEvent;
import ua.coral.ugcc.user.client.uibinder.NewsBinder;
import ua.coral.ugcc.user.client.view.ListNewsView;

import java.util.List;

public class ListNewsPresenter extends DefaultPresenterImpl implements Presenter, ListNewsView.Presenter {

    private final UgccServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public ListNewsPresenter(final UgccServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        listNews();
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view);
    }

    @Override
    public void countNews() {
//        delegate.countNews();
    }

    @Override
    public void listNews() {
        rpcService.listNews(new AsyncCallback<List<News>>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final List<News> newsList) {
                onNews(newsList);
            }
        });
    }

    @Override
    public void openNews(final Long newsId) {
        eventBus.fireEvent(new OpenedNewsEvent(newsId));
    }

    private void onNews(final List<News> newsList) {
        view.setChild(new NewsBinder(newsList, this));
    }
}
