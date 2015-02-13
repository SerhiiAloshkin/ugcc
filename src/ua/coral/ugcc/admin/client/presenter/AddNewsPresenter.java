package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.view.AddNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AddNewsPresenter implements Presenter, AddNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final AddNewsView view;

    public AddNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus,
                            final AddNewsView view) {
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
    public void addNews(final News news) {
        rpcService.addNews(news, new AsyncCallback<Void>() {
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
