package ua.coral.ugcc.admin.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.uibinder.AddNewsBinder;
import ua.coral.ugcc.admin.client.view.AddNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

public class AddNewsPresenter extends DefaultPresenterImpl implements Presenter, AddNewsView.Presenter {

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public AddNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        view.setChild(new AddNewsBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void addNews(final News news, final AddNewsBinder addView) {
        rpcService.addNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                addView.saveFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                addView.saveSuccessful();
                eventBus.fireEvent(new ListNewsEvent());
            }
        });
    }

    public void sendFile(final String fileName, final AddNewsBinder addView) {
        rpcService.sendFile(fileName, new AsyncCallback<String>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final String result) {
                addView.uploaded(result);
            }
        });
    }

    @Override
    public void listNews() {
        eventBus.fireEvent(new ListNewsEvent());
    }
}
