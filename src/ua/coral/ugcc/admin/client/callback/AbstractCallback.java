package ua.coral.ugcc.admin.client.callback;

import ua.coral.ugcc.common.view.View;

public abstract class AbstractCallback<V extends View> {

    private V view;

    protected AbstractCallback(final V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
