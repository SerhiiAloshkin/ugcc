package ua.coral.ugcc.common.event;

import com.google.gwt.event.shared.GwtEvent;
import ua.coral.ugcc.common.event.handler.GoToMainEventHandler;

public class GoToMainEvent extends GwtEvent<GoToMainEventHandler> {

    public static Type<GoToMainEventHandler> TYPE = new Type<>();

    @Override
    public Type<GoToMainEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final GoToMainEventHandler handler) {
        handler.onMain(this);
    }
}
