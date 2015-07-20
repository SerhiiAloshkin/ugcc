package ua.coral.ugcc.user.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.user.client.UgccServiceAsync;
import ua.coral.ugcc.user.client.view.OpenedNewsView;

public class OpenedNewsPresenter extends DefaultPresenterImpl implements Presenter, OpenedNewsView.Presenter {

    private final UgccServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public OpenedNewsPresenter(final UgccServiceAsync rpcService, final HandlerManager eventBus, final Long newsIs) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        openNews(newsIs);
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view);
    }

    @Override
    public void openNews(final Long newsId) {
        rpcService.getNewsById(newsId, new AsyncCallback<News>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final News news) {
                onNews(news);
            }
        });
    }

    private void onNews(final News news) {
        //view.setChild(new NewsBinder(newsList, this));
    }
}
