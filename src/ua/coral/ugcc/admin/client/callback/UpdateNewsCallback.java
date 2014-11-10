package ua.coral.ugcc.admin.client.callback;

import ua.coral.ugcc.admin.client.view.ListNewsView;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class UpdateNewsCallback extends AbstractCallback<ListNewsView> implements AsyncCallback<Void> {

    public UpdateNewsCallback(final ListNewsView view) {
        super(view);
    }

    @Override
    public void onFailure(final Throwable throwable) {
        getView().eventUpdateNewsFailed();
    }

    @Override
    public void onSuccess(final Void aVoid) {
        getView().eventUpdateNewsSuccessful();
    }
}
