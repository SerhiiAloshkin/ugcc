package ua.coral.ugcc.common.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class AbstractEntryPoint implements EntryPoint {

    private final SimplePanel simplePanel = new SimplePanel();

    @Override
    public void onModuleLoad() {

    }

    protected abstract Place getPlace();
}
