package ua.coral.ugcc.common.presenter.impl;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import ua.coral.ugcc.common.event.GoToDirectionEvent;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.uibinder.DefaultBinder;
import ua.coral.ugcc.common.uibinder.MapBinder;

public class MapPresenter extends DefaultPresenterImpl implements Presenter {

    private final DefaultBinder view;
    private final HandlerManager eventBus;

    public MapPresenter(final HandlerManager eventBus, final DefaultBinder view) {
        super(eventBus);
        this.eventBus = eventBus;
        this.view = view;

        view.setChild(new MapBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    public void goToDirection() {
        eventBus.fireEvent(new GoToDirectionEvent());
    }
}
