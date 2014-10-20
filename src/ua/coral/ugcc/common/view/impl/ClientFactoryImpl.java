package ua.coral.ugcc.common.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.MainView;
import ua.coral.ugcc.common.view.NewsView;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public MainView getMainView() {
        return GWT.create(MainView.class);
    }

    @Override
    public NewsView getNewsView() {
        return GWT.create(NewsView.class);
    }
}
