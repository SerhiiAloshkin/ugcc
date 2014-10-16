package ua.coral.ugcc.admin.client.view.impl;

import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.MainView;
import ua.coral.ugcc.common.view.NewsView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private final MainView mainView = new MainViewImpl();
    private final NewsView newsView = new NewsViewImpl();

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
        return mainView;
    }

    @Override
    public NewsView getNewsView() {
        return newsView;
    }
}
