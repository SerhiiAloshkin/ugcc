package ua.coral.ugcc.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.coral.ugcc.admin.client.view.MainView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public class AdminModeServiceDelegate {

    private AdminModeServiceAsync async = GWT.create(AdminModeService.class);
    private MainView view;

    public void setView(final MainView view) {
        this.view = view;
    }

    public void listNews() {
        async.listNews(new AsyncCallback<List<News>>() {
            @Override
            public void onFailure(final Throwable caught) {
                view.eventListContactsFailed();
            }

            @Override
            public void onSuccess(final List<News> result) {
                view.eventListRetrievedFromService(result);
            }
        });
    }

    public void addNews(final News news) {
        async.addNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                view.eventAddContactFailed(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                view.eventAddContactSuccessful();
            }
        });
    }

    public void removeNews(final News news) {
        async.removeNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                view.eventRemoveContactFailed();
            }

            @Override
            public void onSuccess(final Void result) {
                view.eventRemoveContactSuccessful();
            }
        });
    }

    public void updateNews(final News news) {
        async.updateNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                view.eventUpdateContactFailed();
            }

            @Override
            public void onSuccess(final Void result) {
                view.eventUpdateSuccessful();
            }
        });
    }
}
