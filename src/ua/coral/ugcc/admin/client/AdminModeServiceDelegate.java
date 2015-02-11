package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.admin.client.callback.AddNewsCallback;
import ua.coral.ugcc.admin.client.callback.CountNewsCallback;
import ua.coral.ugcc.admin.client.callback.ListNewsCallback;
import ua.coral.ugcc.admin.client.callback.RemoveNewsCallback;
import ua.coral.ugcc.admin.client.callback.UpdateNewsCallback;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

public class AdminModeServiceDelegate {

    private final AdminModeServiceAsync async;
    private final ListNewsView view;

    public AdminModeServiceDelegate(final AdminModeServiceAsync async, final ListNewsView view) {
        this.async = async;
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
