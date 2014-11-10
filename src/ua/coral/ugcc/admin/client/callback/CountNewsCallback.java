package ua.coral.ugcc.admin.client.callback;

import ua.coral.ugcc.admin.client.view.ListNewsView;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CountNewsCallback extends AbstractCallback<ListNewsView> implements AsyncCallback<Integer> {

    public CountNewsCallback(final ListNewsView view) {
        super(view);
    }

    @Override
    public void onFailure(final Throwable throwable) {
        getView().eventCountNewsFailed();
    }

    @Override
    public void onSuccess(final Integer count) {
        getView().eventCountNewsSuccessful(count);
    }
}
