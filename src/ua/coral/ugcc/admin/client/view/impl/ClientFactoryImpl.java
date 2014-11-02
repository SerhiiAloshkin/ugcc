package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.admin.client.view.ClientFactory;
import ua.coral.ugcc.admin.client.view.MainView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

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
        return new MainViewImpl();
    }
}
