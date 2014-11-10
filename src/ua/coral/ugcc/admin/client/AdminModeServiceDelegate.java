package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.callback.AddNewsCallback;
import ua.coral.ugcc.admin.client.callback.CountNewsCallback;
import ua.coral.ugcc.admin.client.callback.ListNewsCallback;
import ua.coral.ugcc.admin.client.callback.RemoveNewsCallback;
import ua.coral.ugcc.admin.client.callback.UpdateNewsCallback;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.core.client.GWT;

public class AdminModeServiceDelegate {

    private AdminModeServiceAsync async = GWT.create(AdminModeService.class);
    private ListNewsView view;

    public void setView(final ListNewsView view) {
        this.view = view;
    }

    public void listNews() {
        async.listNews(new ListNewsCallback(view));
    }

    public void countNews() {
        async.countNews(new CountNewsCallback(view));
    }

    public void addNews(final News news) {
        async.addNews(news, new AddNewsCallback(view));
    }

    public void removeNews(final News news) {
        async.removeNews(news, new RemoveNewsCallback(view));
    }

    public void updateNews(final News news) {
        async.updateNews(news, new UpdateNewsCallback(view));
    }
}
