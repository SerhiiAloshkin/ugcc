package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.view.impl.MainViewImpl;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AdminModeServiceDelegate {

    private AdminModeServiceAsync async = GWT.create(AdminModeService.class);
    public MainViewImpl gui;

    public void listNews() {
        async.listNews(new AsyncCallback<List<News>>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.service_eventListContactsFailed(caught);
            }

            @Override
            public void onSuccess(final List<News> result) {
                gui.service_eventListRetrievedFromService(result);
            }
        });
    }

    public void addNews(final News news) {
        async.addNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.service_eventAddContactFailed(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                gui.service_eventAddContactSuccessful();
            }
        });
    }

    public void removeNews(final News news) {
        async.removeNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.service_eventRemoveContactFailed(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                gui.service_eventRemoveContactSuccessful();
            }
        });
    }

    public void updateNews(final News news) {
        async.updateNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.service_eventUpdateContactFailed(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                gui.service_eventUpdateSuccessful();
            }
        });
    }
}
