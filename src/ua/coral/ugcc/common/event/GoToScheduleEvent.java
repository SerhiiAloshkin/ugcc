package ua.coral.ugcc.common.event;

import ua.coral.ugcc.common.event.handler.GoToScheduleEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class GoToScheduleEvent extends GwtEvent<GoToScheduleEventHandler> {

    public static Type<GoToScheduleEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToScheduleEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToScheduleEventHandler handler) {
        handler.onSchedule(this);
    }
}
