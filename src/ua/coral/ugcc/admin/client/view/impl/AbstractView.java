package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.common.view.View;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class AbstractView extends Composite implements View {

    private Presenter presenter;

    public AbstractView() {
        getContent().draw();
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    protected abstract HLayout getContent();
}
