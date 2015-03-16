package ua.coral.ugcc.common.presenter.impl;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.common.uibinder.DirectionBinder;

public class DirectionPresenter extends DefaultPresenterImpl implements Presenter {

    private final DefaultBinder view;
    private final HandlerManager eventBus;

    public DirectionPresenter(final HandlerManager eventBus, final DefaultBinder view) {
        super(eventBus);
        this.eventBus = eventBus;
        this.view = view;

        view.setChild(new DirectionBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    public void goToMap() {
        eventBus.fireEvent(new GoToMapEvent());
    }
}
