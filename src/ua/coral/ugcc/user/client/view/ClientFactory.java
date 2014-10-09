package ua.coral.ugcc.user.client.view;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {

    EventBus getEventBus();
    PlaceController getPlaceController();
    MainView getMainView();
    NewsView getNewsView();
}
