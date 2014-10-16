package ua.coral.ugcc.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.coral.ugcc.admin.client.view.impl.MainViewImpl;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

public class AdminModeServiceDelegate {

    private AdminModeServiceAsync async = GWT.create(AdminModeService.class);
    private MainViewImpl gui;

    public void setGui(final MainViewImpl gui) {
        this.gui = gui;
    }

    public void listNews() {
        async.listNews(new AsyncCallback<List<News>>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.eventListContactsFailed();
            }

            @Override
            public void onSuccess(final List<News> result) {
                gui.eventListRetrievedFromService(result);
            }
        });
    }

    public void addNews(final News news) {
        async.addNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.eventAddContactFailed(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                gui.eventAddContactSuccessful();
            }
        });
    }

    public void removeNews(final News news) {
        async.removeNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.eventRemoveContactFailed();
            }

            @Override
            public void onSuccess(final Void result) {
                gui.eventRemoveContactSuccessful();
            }
        });
    }

    public void updateNews(final News news) {
        async.updateNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                gui.eventUpdateContactFailed();
            }

            @Override
            public void onSuccess(final Void result) {
                gui.eventUpdateSuccessful();
            }
        });
    }
}
