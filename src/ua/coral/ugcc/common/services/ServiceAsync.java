package ua.coral.ugcc.common.services;

import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync {

    void addNews(News news, AsyncCallback<Void> callback);

    void removeNews(News news, AsyncCallback<Void> callback);

    void updateNews(News news, AsyncCallback<Void> callback);

    void listNews(AsyncCallback<List<News>> callback);

    void countNews(AsyncCallback<Integer> callback);
}
