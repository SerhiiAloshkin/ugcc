package ua.coral.ugcc.common.presenter.impl;

import com.google.gwt.event.shared.HandlerManager;
import ua.coral.ugcc.common.event.GoToContactsEvent;
import ua.coral.ugcc.common.event.GoToMainEvent;
import ua.coral.ugcc.common.event.GoToMapEvent;
import ua.coral.ugcc.common.event.GoToNewsEvent;
import ua.coral.ugcc.common.event.GoToParishEvent;
import ua.coral.ugcc.common.event.GoToScheduleEvent;
import ua.coral.ugcc.common.presenter.DefaultPresenter;

public class DefaultPresenterImpl implements DefaultPresenter {

    private final HandlerManager eventBus;

    public DefaultPresenterImpl(final HandlerManager eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void goToMain() {
        eventBus.fireEvent(new GoToMainEvent());
    }

    @Override
    public void goToNews() {
        eventBus.fireEvent(new GoToNewsEvent());
    }

    @Override
    public void goToParish() {
        eventBus.fireEvent(new GoToParishEvent());
    }

    @Override
    public void goToSchedule() {
        eventBus.fireEvent(new GoToScheduleEvent());
    }

    @Override
    public void goToContacts() {
        eventBus.fireEvent(new GoToContactsEvent());
    }

    @Override
    public void goToMap() {
        eventBus.fireEvent(new GoToMapEvent());
    }
}
