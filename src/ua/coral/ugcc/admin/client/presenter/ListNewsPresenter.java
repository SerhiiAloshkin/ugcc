package ua.coral.ugcc.admin.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.AddNewsEvent;
import ua.coral.ugcc.admin.client.event.UpdateNewsEvent;
import ua.coral.ugcc.admin.client.uibinder.news.NewsBinder;
import ua.coral.ugcc.admin.client.uibinder.news.SingleNewsBinder;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import java.util.List;

public class ListNewsPresenter extends DefaultPresenterImpl implements Presenter, ListNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public ListNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
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
    public void addNews() {
        eventBus.fireEvent(new AddNewsEvent());
    }

    @Override
    public void removeNews(final News news, final SingleNewsBinder view) {
        rpcService.removeNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                view.removeFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                view.removeSuccessful();
                view.removeFromParent();
            }
        });
    }

    @Override
    public void updateNews(final Long newsId) {
        eventBus.fireEvent(new UpdateNewsEvent(newsId));
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

    private void onNews(final List<News> newsList) {
        view.setChild(new NewsBinder(newsList, this));
    }
}
