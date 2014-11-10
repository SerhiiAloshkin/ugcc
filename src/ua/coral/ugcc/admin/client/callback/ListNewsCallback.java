package ua.coral.ugcc.admin.client.callback;

import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListNewsCallback extends AbstractCallback<ListNewsView> implements AsyncCallback<List<News>> {

    public ListNewsCallback(final ListNewsView view) {
        super(view);
    }

    @Override
    public void onFailure(final Throwable throwable) {
        getView().eventListNewsFailed();
    }

    @Override
    public void onSuccess(final List<News> listNews) {
        getView().eventListRetrievedFromService(listNews);
    }
}
