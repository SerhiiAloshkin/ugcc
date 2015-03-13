package ua.coral.ugcc.common.presenter.impl;

import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.common.uibinder.MapBinder;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class MapPresenter extends DefaultPresenterImpl implements Presenter {

    private final DefaultBinder view;

    public MapPresenter(final HandlerManager eventBus, final DefaultBinder view) {
        super(eventBus);
        this.view = view;

        view.setChild(new MapBinder());
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }
}
