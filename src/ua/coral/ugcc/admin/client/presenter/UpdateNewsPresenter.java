package ua.coral.ugcc.admin.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.uibinder.EditNewsBinder;
import ua.coral.ugcc.admin.client.view.UpdateNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

public class UpdateNewsPresenter extends DefaultPresenterImpl implements Presenter, UpdateNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public UpdateNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                               final Long newsId) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        loadNewsById(newsId);
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view);
    }

    private void loadNewsById(final Long newsId) {
        rpcService.getNewsById(newsId, new AsyncCallback<News>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final News news) {
                loadEditView(news);
            }
        });
    }

    private void loadEditView(final News news) {
        view.setChild(new EditNewsBinder(news, this));
    }

    @Override
    public void updateNews(final News news, final EditNewsBinder editView) {
        rpcService.updateNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                editView.saveFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                editView.saveSuccessful();
            }
        });
    }

    @Override
    public void listNews() {
        eventBus.fireEvent(new ListNewsEvent());
    }
}
